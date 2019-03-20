import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.List;  
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;  
import java.util.TreeMap; 
  
  
public class WordCount {  
	
    private static String fileName = "src/sourse.txt";//文本路径     
	public static void main(String[] args) throws Exception {  
		//筛选出文章中的英文单词
        BufferedReader br = new BufferedReader(new FileReader(fileName));  
        List<String> lists = new ArrayList<String>();  			//存储过滤后单词的列表  
        String readLine = null;
		while((readLine = br.readLine()) != null){  
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");   //过滤出只含有字母的  
            for (String word : wordsArr1) {  
                if(word.length() != 0){  						//去除长度为0的行  
                    lists.add(word);  
                }  
            }  
        }            
        br.close();  

        // 排序 ，单词的词频统计       			             
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数                 
       
        for (String li : lists) {  
            if(wordsCount.get(li) != null){  
                wordsCount.put(li,wordsCount.get(li) + 1);  
            }else{  
                wordsCount.put(li,1);  
            }
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordsCount.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue(); // 降序排列
            }
        });    
            
            boolean flag = true;//设置一个标签来让do-while循环判断是否继续循环
            do{
            	System.out.println("*********************************************单词统计菜单***********************************************");
                System.out.println("\t\t1  指定单词词频统计");
                System.out.println("\t\t2  显示前k个单词的词频统计");
                System.out.println("\t\t3  统计文本所有单词数量及词频数,将单词数和词频数按字典顺序写入到result.txt文件中");
                System.out.println("\t\t0  退出！");
                System.out.println("\t\t选择对应的数字选项即可进入对应的功能");
                System.out.println("********************************************************************************************************");
                System.out.println("请选择功能: ");
                Scanner sc = new Scanner(System.in);
                int num = sc.nextInt();
            	switch (num){               
                case 1:
                	System.out.println("请输入你要查询的单词：");
                    WordNum(list);
                    break;
                case 2:
                	System.out.println("请输入你要查的单词数: ");
                    Before(list);
                    break;
                case 3:                  
					WriteTxt(list,fileName);
                    break;           
            	}
            	//当case break后,提醒用户输入 0 返回菜单
    			System.out.println("输入0返回菜单.");
    			int exit = sc.nextInt();
    			//当键入的exit值为 0 时, flag赋值为 false
    			if(exit == 0){
    				flag = false;
    			}
    		}while(!flag);
            System.out.println("你已经退出");
    }
    public static void WordNum(ArrayList<Map.Entry<String, Integer>> list) {
        Scanner s1 = new Scanner(System.in);       
        String word1 = s1.nextLine();
        String word2[] = word1.split(" ");
        for (String li : word2) {
            for (Map.Entry<String, Integer> entry : list) {

                if (li.equals(entry.getKey())) {
                    int num = entry.getValue();
                    System.out.print(entry.getKey()+"\tnumbers "+entry.getValue()+":");
                    for (int i = 0; i < num / 4; i++) {
                        System.out.print("X");
                    }
                    System.out.println();
                    System.out.println();
                    break;
                }
            }
        }
    }
    public static void Before(ArrayList<Map.Entry<String, Integer>> list) {
        Scanner s = new Scanner(System.in);
        
        int k = s.nextInt();
        for (int i = 0; i < k; i++) {
            System.out.println(list.get(i));
        }
    }

    public static void WriteTxt(ArrayList<Map.Entry<String, Integer>> list,String fileName) {
        int count = 0 ;
        FileWriter w = null;
        try {
            w = new FileWriter("src/result.txt");
        } catch (IOException e) {
            System.out.println("此文件已存在！");
        }
        BufferedWriter b = new BufferedWriter(w);

        for (Map.Entry<String, Integer> entry : list) {
            try {
                b.write(entry.getKey() + "\t\t\tnumber:" + entry.getValue() + "\n");
            } catch (IOException e) {
            	
            }
            try {
                b.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            b.write("单词的总数是:" + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入文件成功!  " + "\n"+"文件路径: " + fileName);
        System.exit(0);
    }
}
