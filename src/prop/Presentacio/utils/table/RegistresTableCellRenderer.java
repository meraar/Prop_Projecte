package prop.Presentacio.utils.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Pere Grau
 */
public class RegistresTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        RegistresTableModel model = (RegistresTableModel) table.getModel();

        // Centrem els valors
        setHorizontalAlignment(JLabel.CENTER);

        if (model.filaEliminada(row)) {
            // Color vermell si la fila està eliminada
            setBackground(Color.red.darker());
        }
        else if (model.valorModificat(row, column)) {
            // Color verd si el valor està modificat
            setBackground(Color.green.darker());
        }
        else {
            // Color per defecte
            setBackground(isSelected ? table.getSelectionBackground() : Color.white);
        }

        return c;

    }
}
