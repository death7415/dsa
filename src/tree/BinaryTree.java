package tree;

public class BinaryTree<T extends Comparable<T>> {

    private Node root;
    private int length;

    class Node{
        T value;
        Node left;
        Node right;

        Node(T value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void add(T value){
        var node = new Node(value);
        var pointer = root;
        if (root == null){
            this.root = node;
            length++;
        }else {
            while (true){
                if (value.compareTo(pointer.value) < 0){
                    if (pointer.left == null){
                        pointer.left = node;
                        break;
                    }else {
                        pointer = pointer.left;
                    }
                }else if (value.compareTo(pointer.value) >  0){
                    if (pointer.right == null){
                        pointer.right = node;
                        break;
                    }else {
                        pointer = pointer.right;
                    }
                }else if (value.compareTo(pointer.value) ==  0){
                    break;
                }
            }
        }
    }

    public T getRoot(){
        return this.root != null ? root.value : null;
    }

    public int getLength(){
        return this.length;
    }

    public boolean contains(T value){
        if (root != null){
            var pointer = root;
            while (pointer != null){
                if (value.compareTo(pointer.value) == 0){
                    return true;
                }else if (value.compareTo(pointer.value) < 0){
                    pointer = pointer.left;
                }else {
                    pointer = pointer.right;
                }
            }
        }
        return false;
    }

    public boolean rContains(T value){
        return rContains(root, value);
    }

    private boolean rContains(Node current, T value){
        if (current == null || value == null) return false;
        else if (current.value == value) return true;
        else if (value.compareTo(current.value) < 0){
            return rContains(current.left, value);
        }else {
            return rContains(current.right, value);
        }
    }

    public void rInsert(T value){
        rInsert(root, value);
    }

    private Node rInsert(Node current, T value){
        Node node = new Node(value);
        if (current == null){
            root = node;
            return node;
        }else if (value.compareTo(current.value) < 0){
            current.left = rInsert(current.left, value);
        }else if (value.compareTo(current.value) > 0){
            current.right = rInsert(current.right, value);
        }
        return current;
    }

    public void rDelete(T value){
        root = rDelete(root, value);
    }

    private T minimumValue(Node pointer){
        while (pointer.left != null){
            pointer = pointer.left;
        }
        return pointer.value;
    }

    private Node rDelete(Node currentNode, T value){
        if (currentNode != null){
            if (value.compareTo(currentNode.value) < 0){
                currentNode.left = rDelete(currentNode.left, value);
            }else if (value.compareTo(currentNode.value) > 0){
                currentNode.right = rDelete(currentNode.right, value);
            }else {
                if (currentNode.left == null && currentNode.right == null){
                    return null;
                }else if (currentNode.left == null){
                    currentNode = currentNode.right;
                } else if (currentNode.right == null) {
                    currentNode = currentNode.left;
                }else {
                    var minValue = minimumValue(currentNode.right);
                    currentNode.value = minValue;
                    currentNode.right = rDelete(currentNode.right, minValue);
                }
            }
            return currentNode;
        }
        return null;
    }
}
