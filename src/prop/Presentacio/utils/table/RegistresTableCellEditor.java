package prop.Presentacio.utils.table;

import javax.swing.*;
import java.awt.*;

/**
 * @author Pere Grau
 */
public class RegistresTableCellEditor extends DefaultCellEditor {
    /**
     * Constructora amb TextField.
     *
     * @param textField TextField de la cel·la.
     */
    public RegistresTableCellEditor(JTextField textField) {
        super(textField);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        RegistresTableModel model = (RegistresTableModel) table.getModel();
        String valor = model.getValueAt(row, column).toString();

        // Guardem el valor anterior per si necessitem restablir-lo
        model.setCurrentEditing(valor);

        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
