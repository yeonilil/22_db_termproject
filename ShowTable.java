package workshop;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ShowTable extends AbstractTableModel {
    private String[] columnNames = {"ID", "PWD"};
    private ArrayList<ToTable> datas;

    public ShowTable(String strEname ) throws ClassNotFoundException, SQLException {
        Database dao = new Database();
        datas = dao.searchEname( strEname );
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String result = "";
        ToTable to = datas.get(rowIndex);
        switch( columnIndex ) {
            case 0:
                result = to.getId();
                break;
            case 1:
                result = to.getPwd();
                break;

        }
        return result;
    }
}
