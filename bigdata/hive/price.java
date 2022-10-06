// ORM class for table 'price'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Sep 28 15:15:03 KST 2022
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

public class price extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.id = (Long)value;
      }
    });
    setters.put("date_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.date_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("price", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.price = (Integer)value;
      }
    });
    setters.put("quantity", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.quantity = (String)value;
      }
    });
    setters.put("unit", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.unit = (String)value;
      }
    });
    setters.put("ingredient_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.ingredient_id = (Long)value;
      }
    });
    setters.put("store_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.store_id = (Long)value;
      }
    });
    setters.put("type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        price.this.type = (Integer)value;
      }
    });
  }
  public price() {
    init0();
  }
  private Long id;
  public Long get_id() {
    return id;
  }
  public void set_id(Long id) {
    this.id = id;
  }
  public price with_id(Long id) {
    this.id = id;
    return this;
  }
  private java.sql.Timestamp date_time;
  public java.sql.Timestamp get_date_time() {
    return date_time;
  }
  public void set_date_time(java.sql.Timestamp date_time) {
    this.date_time = date_time;
  }
  public price with_date_time(java.sql.Timestamp date_time) {
    this.date_time = date_time;
    return this;
  }
  private Integer price;
  public Integer get_price() {
    return price;
  }
  public void set_price(Integer price) {
    this.price = price;
  }
  public price with_price(Integer price) {
    this.price = price;
    return this;
  }
  private String quantity;
  public String get_quantity() {
    return quantity;
  }
  public void set_quantity(String quantity) {
    this.quantity = quantity;
  }
  public price with_quantity(String quantity) {
    this.quantity = quantity;
    return this;
  }
  private String unit;
  public String get_unit() {
    return unit;
  }
  public void set_unit(String unit) {
    this.unit = unit;
  }
  public price with_unit(String unit) {
    this.unit = unit;
    return this;
  }
  private Long ingredient_id;
  public Long get_ingredient_id() {
    return ingredient_id;
  }
  public void set_ingredient_id(Long ingredient_id) {
    this.ingredient_id = ingredient_id;
  }
  public price with_ingredient_id(Long ingredient_id) {
    this.ingredient_id = ingredient_id;
    return this;
  }
  private Long store_id;
  public Long get_store_id() {
    return store_id;
  }
  public void set_store_id(Long store_id) {
    this.store_id = store_id;
  }
  public price with_store_id(Long store_id) {
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
  public price with_type(Integer type) {
    this.type = type;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof price)) {
      return false;
    }
    price that = (price) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.date_time == null ? that.date_time == null : this.date_time.equals(that.date_time));
    equal = equal && (this.price == null ? that.price == null : this.price.equals(that.price));
    equal = equal && (this.quantity == null ? that.quantity == null : this.quantity.equals(that.quantity));
    equal = equal && (this.unit == null ? that.unit == null : this.unit.equals(that.unit));
    equal = equal && (this.ingredient_id == null ? that.ingredient_id == null : this.ingredient_id.equals(that.ingredient_id));
    equal = equal && (this.store_id == null ? that.store_id == null : this.store_id.equals(that.store_id));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof price)) {
      return false;
    }
    price that = (price) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.date_time == null ? that.date_time == null : this.date_time.equals(that.date_time));
    equal = equal && (this.price == null ? that.price == null : this.price.equals(that.price));
    equal = equal && (this.quantity == null ? that.quantity == null : this.quantity.equals(that.quantity));
    equal = equal && (this.unit == null ? that.unit == null : this.unit.equals(that.unit));
    equal = equal && (this.ingredient_id == null ? that.ingredient_id == null : this.ingredient_id.equals(that.ingredient_id));
    equal = equal && (this.store_id == null ? that.store_id == null : this.store_id.equals(that.store_id));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.date_time = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.price = JdbcWritableBridge.readInteger(3, __dbResults);
    this.quantity = JdbcWritableBridge.readString(4, __dbResults);
    this.unit = JdbcWritableBridge.readString(5, __dbResults);
    this.ingredient_id = JdbcWritableBridge.readLong(6, __dbResults);
    this.store_id = JdbcWritableBridge.readLong(7, __dbResults);
    this.type = JdbcWritableBridge.readInteger(8, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.date_time = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.price = JdbcWritableBridge.readInteger(3, __dbResults);
    this.quantity = JdbcWritableBridge.readString(4, __dbResults);
    this.unit = JdbcWritableBridge.readString(5, __dbResults);
    this.ingredient_id = JdbcWritableBridge.readLong(6, __dbResults);
    this.store_id = JdbcWritableBridge.readLong(7, __dbResults);
    this.type = JdbcWritableBridge.readInteger(8, __dbResults);
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
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date_time, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(price, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(quantity, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(unit, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(ingredient_id, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(store_id, 7 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(type, 8 + __off, 4, __dbStmt);
    return 8;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date_time, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(price, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(quantity, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(unit, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(ingredient_id, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(store_id, 7 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(type, 8 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.date_time = null;
    } else {
    this.date_time = new Timestamp(__dataIn.readLong());
    this.date_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.price = null;
    } else {
    this.price = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.quantity = null;
    } else {
    this.quantity = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.unit = null;
    } else {
    this.unit = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ingredient_id = null;
    } else {
    this.ingredient_id = Long.valueOf(__dataIn.readLong());
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
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.date_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_time.getTime());
    __dataOut.writeInt(this.date_time.getNanos());
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
    Text.writeString(__dataOut, quantity);
    }
    if (null == this.unit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, unit);
    }
    if (null == this.ingredient_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ingredient_id);
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
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.date_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_time.getTime());
    __dataOut.writeInt(this.date_time.getNanos());
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
    Text.writeString(__dataOut, quantity);
    }
    if (null == this.unit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, unit);
    }
    if (null == this.ingredient_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.ingredient_id);
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
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_time==null?"null":"" + date_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(price==null?"null":"" + price, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quantity==null?"null":quantity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(unit==null?"null":unit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ingredient_id==null?"null":"" + ingredient_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(store_id==null?"null":"" + store_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":"" + type, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_time==null?"null":"" + date_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(price==null?"null":"" + price, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(quantity==null?"null":quantity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(unit==null?"null":unit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ingredient_id==null?"null":"" + ingredient_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(store_id==null?"null":"" + store_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":"" + type, delimiters));
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
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.date_time = null; } else {
      this.date_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.price = null; } else {
      this.price = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n")) { this.quantity = null; } else {
      this.quantity = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n")) { this.unit = null; } else {
      this.unit = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.ingredient_id = null; } else {
      this.ingredient_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.store_id = null; } else {
      this.store_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.type = null; } else {
      this.type = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.date_time = null; } else {
      this.date_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.price = null; } else {
      this.price = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n")) { this.quantity = null; } else {
      this.quantity = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n")) { this.unit = null; } else {
      this.unit = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.ingredient_id = null; } else {
      this.ingredient_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.store_id = null; } else {
      this.store_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\n";
    }
    if (__cur_str.equals("\n") || __cur_str.length() == 0) { this.type = null; } else {
      this.type = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    price o = (price) super.clone();
    o.date_time = (o.date_time != null) ? (java.sql.Timestamp) o.date_time.clone() : null;
    return o;
  }

  public void clone0(price o) throws CloneNotSupportedException {
    o.date_time = (o.date_time != null) ? (java.sql.Timestamp) o.date_time.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("date_time", this.date_time);
    __sqoop$field_map.put("price", this.price);
    __sqoop$field_map.put("quantity", this.quantity);
    __sqoop$field_map.put("unit", this.unit);
    __sqoop$field_map.put("ingredient_id", this.ingredient_id);
    __sqoop$field_map.put("store_id", this.store_id);
    __sqoop$field_map.put("type", this.type);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("date_time", this.date_time);
    __sqoop$field_map.put("price", this.price);
    __sqoop$field_map.put("quantity", this.quantity);
    __sqoop$field_map.put("unit", this.unit);
    __sqoop$field_map.put("ingredient_id", this.ingredient_id);
    __sqoop$field_map.put("store_id", this.store_id);
    __sqoop$field_map.put("type", this.type);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
