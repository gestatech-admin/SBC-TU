/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.sbc.jms;

import at.ac.tuwien.sbc.ClockListener;
import at.ac.tuwien.sbc.DistributorConnector;
import at.ac.tuwien.sbc.Subscription;
import at.ac.tuwien.sbc.model.Clock;
import at.ac.tuwien.sbc.model.ClockType;
import at.ac.tuwien.sbc.model.DistributorDemand;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author Christian
 */
public class JmsDistributorConnector extends AbstractJmsComponent implements DistributorConnector {

    private final UUID distributorId;

    private URI distributorUri;
    private JmsDistributorStockConnector stockConnector;

    private Queue clockQueue;

    // Distributor stuff
    private Queue distributorDemandQueue;
    private MessageProducer distributorDemandQueueProducer;

    private DistributorDemand lastDemand;

    public JmsDistributorConnector(UUID distributorId, int serverPort) {
        super(serverPort);
        this.distributorId = distributorId;
    }

    private void connectDistributor() throws JMSException {
        if (distributorUri != null) {
            return;
        }

        // Connect queues and topics
        connectDistributor0();

        // Start distributor server
        distributorUri = JmsServer.startServer(distributorId.toString());
        // The following uses the first ip address it can find as host instead of localhost
        // distributorURI = URI.create("tcp://" + SbcUtils.getLocalIpAddress() + ":" + distributorURI.getPort());

        // Connect stock connector to distributor server
        stockConnector = new JmsDistributorStockConnector(distributorUri, distributorId.toString());
    }

    private void connectDistributor0() throws JMSException {
        distributorDemandQueue = createQueueIfNull(distributorDemandQueue, JmsConstants.DISTRIBUTOR_DEMAND_QUEUE);
        distributorDemandQueueProducer = createProducerIfNull(distributorDemandQueueProducer, distributorDemandQueue);

        clockQueue = createQueueIfNull(clockQueue, JmsConstants.CLOCK_QUEUE);
    }

    @Override
    public void setDemand(final Map<ClockType, Integer> demand) {
        tm.transactional(new TransactionalWork() {

            @Override
            public void doWork() throws JMSException {
                connectDistributor();

                if (lastDemand != null) {
                    MessageConsumer consumer = null;
                    try {
                        consumer = session.createConsumer(distributorDemandQueue, JmsConstants.DISTRIBUTOR_ID + "='"
                                                          + distributorId.toString() + "'");
                        // This is like a take-operation
                        consumer.receive();
                    } finally {
                        if (consumer != null) {
                            consumer.close();
                        }
                    }
                }

                DistributorDemand distributorDemand = lastDemand = new DistributorDemand(distributorUri, distributorId
                                                                                         .toString(), demand);
                ObjectMessage msg = session.createObjectMessage(distributorDemand);
                msg.setStringProperty(JmsConstants.DISTRIBUTOR_ID, distributorId.toString());
                distributorDemandQueueProducer.send(msg);
            }
        });
    }

    @Override
    public void removeClockFromStock(final Clock removedClock) {
        try {
            connectDistributor();
            stockConnector.removeClockFromStock(removedClock);
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Subscription subscribeForDistributorDeliveries(ClockListener listener) {
        try {
            connectDistributor();
            return stockConnector.subscribeForDistributorDeliveries(listener);
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }
}
