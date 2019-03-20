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
	
    private static String fileName = "src/sourse.txt";//�ı�·��     
	public static void main(String[] args) throws Exception {  
		//ɸѡ�������е�Ӣ�ĵ���
        BufferedReader br = new BufferedReader(new FileReader(fileName));  
        List<String> lists = new ArrayList<String>();  			//�洢���˺󵥴ʵ��б�  
        String readLine = null;
		while((readLine = br.readLine()) != null){  
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");   //���˳�ֻ������ĸ��  
            for (String word : wordsArr1) {  
                if(word.length() != 0){  						//ȥ������Ϊ0����  
                    lists.add(word);  
                }  
            }  
        }            
        br.close();  

        // ���� �����ʵĴ�Ƶͳ��       			             
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //�洢���ʼ�����Ϣ��keyֵΪ���ʣ�valueΪ������                 
       
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
                return o2.getValue() - o1.getValue(); // ��������
            }
        });    
            
            boolean flag = true;//����һ����ǩ����do-whileѭ���ж��Ƿ����ѭ��
            do{
            	System.out.println("*********************************************����ͳ�Ʋ˵�***********************************************");
                System.out.println("\t\t1  ָ�����ʴ�Ƶͳ��");
                System.out.println("\t\t2  ��ʾǰk�����ʵĴ�Ƶͳ��");
                System.out.println("\t\t3  ͳ���ı����е�����������Ƶ��,���������ʹ�Ƶ�����ֵ�˳��д�뵽result.txt�ļ���");
                System.out.println("\t\t0  �˳���");
                System.out.println("\t\tѡ���Ӧ������ѡ��ɽ����Ӧ�Ĺ���");
                System.out.println("********************************************************************************************************");
                System.out.println("��ѡ����: ");
                Scanner sc = new Scanner(System.in);
                int num = sc.nextInt();
            	switch (num){               
                case 1:
                	System.out.println("��������Ҫ��ѯ�ĵ��ʣ�");
                    WordNum(list);
                    break;
                case 2:
                	System.out.println("��������Ҫ��ĵ�����: ");
                    Before(list);
                    break;
                case 3:                  
					WriteTxt(list,fileName);
                    break;           
            	}
            	//��case break��,�����û����� 0 ���ز˵�
    			System.out.println("����0���ز˵�.");
    			int exit = sc.nextInt();
    			//�������exitֵΪ 0 ʱ, flag��ֵΪ false
    			if(exit == 0){
    				flag = false;
    			}
    		}while(!flag);
            System.out.println("���Ѿ��˳�");
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
            System.out.println("���ļ��Ѵ��ڣ�");
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
            b.write("���ʵ�������:" + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("д���ļ��ɹ�!  " + "\n"+"�ļ�·��: " + fileName);
        System.exit(0);
    }
}
