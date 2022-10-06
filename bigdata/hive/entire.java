// ORM class for table 'entire'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Oct 02 22:06:27 KST 2022
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class entire extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("date_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.date_time = (String)value;
      }
    });
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.id = (Long)value;
      }
    });
    setters.put("ingredient_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.ingredient_id = (Long)value;
      }
    });
    setters.put("price", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.price = (Integer)value;
      }
    });
    setters.put("quantity", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.quantity = (Double)value;
      }
    });
    setters.put("store_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.store_id = (Long)value;
      }
    });
    setters.put("type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.type = (Integer)value;
      }
    });
    setters.put("unit", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        entire.this.unit = (String)value;
      }
    });
  }
  public entire() {
    init0();
  }
  private String date_time;
  public String get_date_time() {
    return date_time;
  }
  public void set_date_time(String date_time) {
    this.date_time = date_time;
  }
  public entire with_date_time(String date_time) {
    this.date_time = date_time;
    return this;
  }
  private Long id;
  public Long get_id() {
    return id;
  }
  public void set_id(Long id) {
    this.id = id;
  }
  public entire with_id(Long id) {
    this.id = id;
    return this;
  }
  private Long ingredient_id;
  public Long get_ingredient_id() {
    return ingredient_id;
  }
  public void set_ingredient_id(Long ingredient_id) {
    this.ingredient_id = ingredient_id;
  }
  public entire with_ingredient_id(Long ingredient_id) {
    this.ingredient_id = ingredient_id;
    return this;
  }
  private Integer price;
  public Integer get_price() {
    return price;
  }
  public void set_price(Integer price) {
    this.price = price;
  }
  public entire with_price(Integer price) {
    this.price = price;
    return this;
  }
  private Double quantity;
  public Double get_quantity() {
    return quantity;
  }
  public void set_quantity(Double quantity) {
    this.quantity = quantity;
  }
  public entire with_quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }
  private Long store_id;
  public Long get_store_id() {
    return store_id;
  }
  public void set_store_id(Long store_id) {
    this.store_id = store_id;
  }
  public entire with_store_id(Long store_id) {
    this.store_id = store_id;
    return this;
  }
  private Integer type;
  public Integer get_type() {
    return type;
  }
  public void set_type(Integer type) {
    this.type = type;
  }
  public entire with_type(Integer type) {
    this.type = type;
    return this;
  }
  private String unit;
  public String get_unit() {
    return unit;
  }
  public void set_unit(String unit) {
    this.unit = unit;
  }
  public entire with_unit(String unit) {
    this.unit = unit;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof entire)) {
      return false;
    }
    entire that = (entire) o;
    boolean equal = true;
    equal = equal && (this.date_time == null ? that.date_time == null : this.date_time.equals(that.date_time));
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.ingredient_id == null ? that.ingredient_id == null : this.ingredient_id.equals(that.ingredient_id));
    equal = equal && (this.price == null ? that.price == null : this.price.equals(that.price));
    equal = equal && (this.quantity == null ? that.quantity == null : this.quantity.equals(that.quantity));
    equal = equal && (this.store_id == null ? that.store_id == null : this.store_id.equals(that.store_id));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    equal = equal && (this.unit == null ? that.unit == null : this.unit.equals(that.unit));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof entire)) {
      return false;
    }
    entire that = (entire) o;
    boolean equal = true;
    equal = equal && (this.date_time == null ? that.date_time == null : this.date_time.equals(that.date_time));
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.ingredient_id == null ? that.ingredient_id == null : this.ingredient_id.equals(that.ingredient_id));
    equal = equal && (this.price == null ? that.price == null : this.price.equals(that.price));
    equal = equal && (this.quantity == null ? that.quantity == null : this.quantity.equals(that.quantity));
    equal = equal && (this.store_id == null ? that.store_id == null : this.store_id.equals(that.store_id));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    equal = equal && (this.unit == null ? that.unit == null : this.unit.equals(that.unit));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.date_time = JdbcWritableBridge.readString(1, __dbResults);
    this.id = JdbcWritableBridge.readLong(2, __dbResults);
    this.ingredient_id = JdbcWritableBridge.readLong(3, __dbResults);
    this.price = JdbcWritableBridge.readInteger(4, __dbResults);
    this.quantity = JdbcWritableBridge.readDouble(5, __dbResults);
    this.store_id = JdbcWritableBridge.readLong(6, __dbResults);
    this.type = JdbcWritableBridge.readInteger(7, __dbResults);
    this.unit = JdbcWritableBridge.readString(8, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.date_time = JdbcWritableBridge.readString(1, __dbResults);
    this.id = JdbcWritableBridge.readLong(2, __dbResults);
    this.ingredient_id = JdbcWritableBridge.readLong(3, __dbResults);
    this.price = JdbcWritableBridge.readInteger(4, __dbResults);
    this.quantity = JdbcWritableBridge.readDouble(5, __dbResults);
    this.store_id = JdbcWritableBridge.readLong(6, __dbResults);
    this.type = JdbcWritableBridge.readInteger(7, __dbResults);
    this.unit = JdbcWritableBridge.readString(8, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(date_time, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(id, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(ingredient_id, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(price, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(quantity, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeLong(store_id, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(type, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(unit, 8 + __off, 12, __dbStmt);
    return 8;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(date_time, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(id, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(ingredient_id, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(price, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(quantity, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeLong(store_id, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(type, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(unit, 8 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.date_time = null;
    } else {
    this.date_time = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.ingredient_id = null;
    } else {
    this.ingredient_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.price = null;
    } else {
    this.price = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.quantity = null;
    } else {
    this.quantity = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.store_id = null;
    } else {
    this.store_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.type = null;
    } else {
    this.type = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.unit = null;
    } else {
    this.unit = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.date_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, date_time);
    }
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.ingredient_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ingredient_id);
    }
    if (null == this.price) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.price);
    }
    if (null == this.quantity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.quantity);
    }
    if (null == this.store_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.store_id);
    }
    if (null == this.type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.type);
    }
    if (null == this.unit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, unit);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.date_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, date_time);
    }
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.ingredient_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ingredient_id);
    }
    if (null == this.price) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.price);
    }
    if (null == this.quantity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.quantity);
    }
    if (null == this.store_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.store_id);
    }
    if (null == this.type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.type);
    }
    if (null == this.unit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, unit);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(date_time==null?"null":date_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ingredient_id==null?"null":"" + ingredient_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(price==null?"null":"" + price, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quantity==null?"null":"" + quantity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(store_id==null?"null":"" + store_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":"" + type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(unit==null?"null":unit, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(date_time==null?"null":date_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ingredient_id==null?"null":"" + ingredient_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(price==null?"null":"" + price, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quantity==null?"null":"" + quantity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(store_id==null?"null":"" + store_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":"" + type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(unit==null?"null":unit, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.date_time = null; } else {
      this.date_time = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ingredient_id = null; } else {
      this.ingredient_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.price = null; } else {
      this.price = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.quantity = null; } else {
      this.quantity = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.store_id = null; } else {
      this.store_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.type = null; } else {
      this.type = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.unit = null; } else {
      this.unit = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.date_time = null; } else {
      this.date_time = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ingredient_id = null; } else {
      this.ingredient_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.price = null; } else {
      this.price = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.quantity = null; } else {
      this.quantity = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.store_id = null; } else {
      this.store_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.type = null; } else {
      this.type = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.unit = null; } else {
      this.unit = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    entire o = (entire) super.clone();
    return o;
  }

  public void clone0(entire o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("date_time", this.date_time);
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("ingredient_id", this.ingredient_id);
    __sqoop$field_map.put("price", this.price);
    __sqoop$field_map.put("quantity", this.quantity);
    __sqoop$field_map.put("store_id", this.store_id);
    __sqoop$field_map.put("type", this.type);
    __sqoop$field_map.put("unit", this.unit);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("date_time", this.date_time);
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("ingredient_id", this.ingredient_id);
    __sqoop$field_map.put("price", this.price);
    __sqoop$field_map.put("quantity", this.quantity);
    __sqoop$field_map.put("store_id", this.store_id);
    __sqoop$field_map.put("type", this.type);
    __sqoop$field_map.put("unit", this.unit);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
