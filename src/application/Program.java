package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Program {

	public static void main(String[] args) {
		
		//Input
		String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliquas el";
		List<String> queries = Arrays.asList("a", "em", "i", "el");
		int k = 3;
		
		//Output
		System.out.println(calculaTopOcorrenciaDeQueries(texto, queries, k));

	}
	public static List<String> calculaTopOcorrenciaDeQueries(String texto, List<String> queries, Integer k){
		
		//Variaveis de configuração
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		int[] counter = {0,0,0,0};
		
		
		//Splitando o texto e colocando em um vetor
		String[] allChar = texto.split(" ");
		
		//Percorre a lista e adiciona cada string em suas posições no HashMap
		for(int i = 0; i < queries.size(); i ++) {
			count.put(queries.get(i), 0);
		}
		
		//Filtra a String splitada e compara com "em" e "el"
		for(String st : allChar) {
			if(st.contains(queries.get(1))) counter[1] += 1;
			else if(st.contains(queries.get(3))) counter[3] += 1;
			
			//Filtra a String splitada por char
			for(int i = 0; i < st.length(); i++) {
				char n = st.charAt(i);
				if(n == 'a') counter[0] += 1;
				else if(n == 'i') counter[2] += 1;
				}
			}
		//Adiciona os valores no HashMap
		for(int i = 0; i < counter.length; i++) {
			count.put(queries.get(i), counter[i]);
		}
		//Filtragem do HashMap por numero de k
		List<Entry<String, Integer>> filter = count.entrySet()
				.stream().sorted(Map.Entry.<String, Integer>comparingByValue()
				.reversed()).limit(k).collect(Collectors.toList());
		
		//Coleta os indices da lista de filtragem e adiciona na lista de resultados
		LinkedList<String> result = new LinkedList<>();
		int val = Integer.MIN_VALUE;
		String indice;
		for(Entry<String, Integer> st: filter) {
			if(val <= st.getValue()) {
				val = st.getValue();
				indice = st.getKey();
				result.add(st.getKey());
			}
			if(val > st.getValue()) {
				val = st.getValue();
				indice = st.getKey();
				result.add(st.getKey());
			}
		}
		
		return result;
		}
	}