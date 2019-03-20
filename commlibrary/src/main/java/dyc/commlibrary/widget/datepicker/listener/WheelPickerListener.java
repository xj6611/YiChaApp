package dyc.commlibrary.widget.datepicker.listener;


import dyc.commlibrary.widget.datepicker.bean.DateBean;
import dyc.commlibrary.widget.datepicker.bean.DateType;

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
