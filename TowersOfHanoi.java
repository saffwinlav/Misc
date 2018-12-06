import java.util.Stack;

public class TowersOfHanoi {

	public static void move(int n, Stack<Integer> source, Stack <Integer> target, Stack<Integer> aux) {
		if(n>0) {
			move(n-1, source, aux, target);
			target.push(source.pop());
			move(n-1,aux,target,source);
		}
	}
	public static void main(String [] args) {
		Stack<Integer> tower1 = new Stack<Integer>();
		Stack<Integer> tower2 = new Stack<Integer>();
		Stack<Integer> tower3 = new Stack<Integer>();
		for(int i =4; i>0; i--) {
			tower1.push(i);
		}
		move(4, tower1, tower3, tower2);
		while(tower3.peek() != null) {
			System.out.println(tower3.pop());
		}
	}
}
