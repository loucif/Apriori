
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;

public class Apriori {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		AprioriFrequentItemsetGenerator<String> generator = new AprioriFrequentItemsetGenerator<>();

		List<Set<String>> itemsetList = new ArrayList<>();
		
		itemsetList.add(new HashSet<>(Arrays.asList("a", "b")));
		itemsetList.add(new HashSet<>(Arrays.asList("b", "c", "d")));
		itemsetList.add(new HashSet<>(Arrays.asList("a", "c", "d", "e")));
		itemsetList.add(new HashSet<>(Arrays.asList("a", "d", "e")));
		itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "c")));
		itemsetList.add(new HashSet<>(Arrays.asList("loucif", "b", "c", "d")));
		itemsetList.add(new HashSet<>(Arrays.asList("a")));
		itemsetList.add(new HashSet<>(Arrays.asList("loucif", "b", "c")));
		itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "d")));
		itemsetList.add(new HashSet<>(Arrays.asList("b", "c", "e")));
		
		//FrequentItemsetData<String> data = generator.generate(DataSetFile.readArrfFile("C:\\Program Files\\Weka-3-8\\data\\contact-lenses.arff"), 0.2);
		//FrequentItemsetData<String> data = generator.generate(DataSetFile.readTxtFile("census.dat", " "), 0.8);
		//FrequentItemsetData<String> data = generator.generate(itemsetList, 0.2);
		FrequentItemsetData<String> data = generator.generate(DataSetFile.readArrfFile("GroceryStoreDataSet.csv"), 0.1);
		
		int i = 1;
		for (Set<String> itemset : data.getFrequentItemsetList()) {
			 System.out.printf("%2d: %9s, support: %1.1f\n", i++, itemset, data.getSupport(itemset));
		}	
	}
}