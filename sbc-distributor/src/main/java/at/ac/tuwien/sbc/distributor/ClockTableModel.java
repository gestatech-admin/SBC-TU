package at.ac.tuwien.sbc.distributor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import at.ac.tuwien.sbc.model.Clock;

public class ClockTableModel extends AbstractTableModel {

	//    private final ClockList clockList;
	private ClockList clockList;
	private List<Clock> clocks;
	private final String[] columnNames = { "Seriennummer","Uhrtyp"};
	private final Class<?>[] columnTypes = { String.class, String.class};

	public ClockTableModel(ClockList clockList) {
		this.clockList = clockList;
		this.clocks = clockList.getClocks();
	}

	@Override
	public int getRowCount() {
		return clocks.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Clock clock = clocks.get(rowIndex);

		switch (columnIndex) {
		case 0: return clock.getSerialId();
		case 1: return clock.getType().toString();
		}

		return null;
	}

	@Override
	public void fireTableDataChanged() {
		//System.out.println("table data changed");
		this.clocks = clockList.getClocks();
		//System.out.println("now having: "+clocks.size()+" clocks");
		super.fireTableDataChanged();
	}

}