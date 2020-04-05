import java.io.IOException;
import java.util.Hashtable;



public class DBAppTest {




public static void main(String[]args) throws Exception {
	
	String strTableName = "Student";
	DBApp dbApp = new DBApp( );
//	Hashtable htblColNameType = new Hashtable( );
//	htblColNameType.put("id", "java.lang.Integer");
//	htblColNameType.put("name", "java.lang.String");
//	htblColNameType.put("enrolled", "java.lang.Boolean");
//	dbApp.createTable( strTableName, "enrolled", htblColNameType );

	//for(int i=0;i<201;i++) {
		
	
	Hashtable htblColNameValue = new Hashtable( );
	htblColNameValue.put("id", new Integer( 1 ));
	htblColNameValue.put("name", new String("amr" ) );
    htblColNameValue.put("enrolled", ( false ) );
    dbApp.insertIntoTable( strTableName , htblColNameValue );
	//dbApp.updateTable("Student", "1.2", htblColNameValue);
	//}
//	
//	htblColNameValue.clear( );
//	htblColNameValue.put("id", new Integer( 2 ));
//	htblColNameValue.put("name", new String("a" ) );
//	htblColNameValue.put("gpa", new Double( 0.9 ) );
//	dbApp.insertIntoTable( strTableName , htblColNameValue );
	
//	htblColNameValue.put("id", new Integer( 5674567 ));
//	htblColNameValue.put("name", new String("Dalia Noor" ) );
//	htblColNameValue.put("gpa", new Double( 1.25 ) );
//	dbApp.insertIntoTable( strTableName , htblColNameValue );
////	
//	htblColNameValue.put("id", new Integer( 23498 ));
//	htblColNameValue.put("name", new String("John Noor" ) );
//	htblColNameValue.put("gpa", new Double( 1.5 ) );
//	dbApp.insertIntoTable( strTableName , htblColNameValue );
	//dbApp.deleteFromTable( strTableName , htblColNameValue );
	
//	htblColNameValue.clear( );
	//htblColNameValue.put("id", new Integer( 78452 ));
//	htblColNameValue.put("name", new String("Zaky Noor" ) );
//	htblColNameValue.put("gpa", new Double( 0.88 ) );
//	dbApp.insertIntoTable( strTableName , htblColNameValue );
//	dbApp.deleteFromTable( strTableName , htblColNameValue );
}

}
