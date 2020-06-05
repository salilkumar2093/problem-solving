String getColumnName(int coumnNumber){
  if(columnNumber<=0)
    return "";
     
  String[] columnEncoding = {"", "A", ------"Z"}
  if(columnNumber<=26)
    return columnEncoding[coumnNumber];
  
  //findDecimal pattern
  
  int number = columnNumber;
  int divisor = 26;
  
  StringBuilder columnName = new StringBuilder();   
  while(number>0){ 
    int remainder = number%divisor;
    number = number/divisor;  
    if(remainder == 0){
         number--;
         columnName.add(columnEncoding[26]);
    }else {
         columnName.add(columnEncoding[remainder]);
    }
  }
   return columnName.reverse().toString();
}
