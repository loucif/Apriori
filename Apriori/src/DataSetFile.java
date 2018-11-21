import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DataSetFile {
	
	private String dataSetType;
	private String dataSetPath;
	private static List<Set<String>> itemsetList;
	
	
	static final String TYPE_OF_ARRF = "TYPE_OF_ARRF";
	static final String TYPE_OF_TXT = "TYPE_OF_TXT";
	static final String TYPE_OF_UCI = "TYPE_OF_UCI";
	
	public DataSetFile(String dataSetType, String DataSetPath) {
		
		this.setDataSetType(dataSetType);
		this.setDataSetType(dataSetPath);
	}
	
	public void checkDataSetType() {
		
	}
	
	public static List<Set<String>> readArrfFile(String dataSetPath) {

		itemsetList = new ArrayList<>();
		Set<String> lst;
		DataSource dataSource = null;
		Instances instances = null;
		
		try {
			dataSource = new DataSource(dataSetPath);
			instances = dataSource.getDataSet();
		} catch (Exception e) { e.printStackTrace(); }
        
		for (Instance instance : instances) {
			lst = new HashSet<>();
			for (int i = 0; i < instance.numAttributes(); i++) if (instance.attribute(i).isNominal()) lst.add(instance.stringValue(i));
			itemsetList.add(lst);
		}
		
		return itemsetList;
	}
	
	public static List<Set<String>> readTxtFile(String dataSetPath, String separator) throws NumberFormatException, IOException {
		
		itemsetList = new ArrayList<>();
		Set<String> lst;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(dataSetPath));
		
		while (bufferedReader.ready()) {    		
    		
			lst = new HashSet<>();
			String line = bufferedReader.readLine();
			
			if (line.matches("\\s*")) continue;		
			
			StringTokenizer stringTokenizer = new StringTokenizer(line,separator);
    		
			while (stringTokenizer.hasMoreTokens()) { lst.add(stringTokenizer.nextToken()); }
			itemsetList.add(lst);
    	}  
    	
		bufferedReader.close();
		return itemsetList;
	}

	public String getDataSetType() { return dataSetType; }

	public void setDataSetType(String dataSetType) { this.dataSetType = dataSetType; }

	public String getDataSetPath() { return dataSetPath; }

	public void setDataSetPath(String dataSetPath) { this.dataSetPath = dataSetPath; }

	public static List<Set<String>> getItemsetList() { return itemsetList; }

	public static void setItemsetList(List<Set<String>> itemsetList) { DataSetFile.itemsetList = itemsetList; }
	
}