package stack;

public class Stack {
    class Node{
        int value;
        Node next;

        Node(int value){
            this.value = value;
            this.next = null;
        }
    }
    private Node top;
    private int length;

    public void push(int value){
        var node = new Node(value);
        if (top != null) {
            node.next = top;
        }
        top = node;
        length++;
    }

    public Node pop(){
        if (top == null){
            return null;
        }else {
            var popItem = top;
            top = top.next;
            popItem.next = null;
            length --;
            return popItem;
        }
    }

    public Node peek(){
        if (top == null){
            return null;
        }else {
            return top;
        }
    }

    public int getTop(){
       return top != null ? top.value : -1;
    }

    public int getSize(){
        return length;
    }

    public static boolean isBalanced(String str){
        if(str == null || str.isEmpty()){
            return false;
        }else {
            Stack s = new Stack();
            int counter = 0;
            for (char c: str.toCharArray()){
                if(c == '('){
                    s.push(c);
                }else {
                    counter++;
                    s.pop();
                }
            }
            return s.getSize() == 0 && counter == str.length()/2;
        }
    }

    public static Stack sortStack(Stack stack){
        var res = new Stack();
        res.push(Integer.MIN_VALUE);
        if(stack == null || stack.getSize() == 0){
           return null;
        }else if (stack.getSize() == 1){
            return stack;
        }else {
            for(int i = 0; i < stack.getSize(); i++){
                for (int j= 0; j< stack.getSize(); j++){
                }
            }
        }
        return res;
    }

    public void printStack(){
        if (top == null){
            return;
        }else {
            var temp = top;
            while (temp != null){
                System.out.println(temp.value);
                temp = temp.next;
            }
        }
    }
}
