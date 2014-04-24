/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.sbc.actor;

import at.ac.tuwien.sbc.Connector;
import at.ac.tuwien.sbc.TransactionalTask;
import at.ac.tuwien.sbc.model.Clock;
import at.ac.tuwien.sbc.model.ClockQualityType;
import at.ac.tuwien.sbc.util.SbcUtils;

/**
 *
 * @author Christian
 */
public class QualityCheckerActor extends AbstractActor {

    public QualityCheckerActor(Connector connector) {
        super(connector);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: Application PORT (xvsm|jms)");
        }
        
        Connector connector = SbcUtils.getConnector(Integer.parseInt(args[0]), args[1]);
        AbstractActor actor = new QualityCheckerActor(connector);
        Thread t = new Thread(actor);
        
        System.out.println("Starting " + actor.getClass().getSimpleName() + " with id " + actor.getId());
        System.out.println("Press CTRL+C to shutdown...");
        while(System.in.read() != -1);
        t.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            connector.takeAssembled(new TransactionalTask<Clock>() {

                @Override
                public void doWork(Clock clock) {
                    clock.check(id, random.get()
                        .nextInt(10) + 1);
                    connector.addCheckedClock(clock, ClockQualityType.fromQuality(clock.getQuality()));
                }

            });
        }
    }

}
