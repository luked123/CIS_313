/**
 * Created by sammergendahl on 10/11/16.
 */
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        boolean done = false;
        Node<E> temp = root;

        while(!done){
            if (root == null) {
                root = new Node<E>(data);
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                if (temp.getLeftChild() == null){
                    temp.setLeftChild(new Node<E>(data));
                    temp.getLeftChild().setParent(temp);
                    done = true;
                }
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) <= 0){
                if (temp.getRightChild() == null){
                    temp.setRightChild(new Node<E>(data));
                    temp.getRightChild().setParent(temp);
                    done = true;
                }
                temp = temp.getRightChild();
            }
        }
    }

    public Node<E> find(E data){
        boolean done = false;
        Node<E> temp = root;


        while(!done){
            if (temp == null){
                return null;
            }
            if(temp.getData().compareTo(data) == 0){
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) < 0){
                temp = temp.getRightChild();
            }
        }
        return temp;
    }

    public void delete(E data){
        Node<E> temp = find(data);
        if (temp == null){
            return;
        }
        Node<E> replacement = new Node(null);

        boolean isRight;
        boolean isLeft;
        boolean isRoot;

        boolean hasChildren;
        boolean hasLeft;
        boolean hasRight;
        boolean hasBoth;

        // Find out if the node to be deleted is the root or if it is a left/right child
        if (temp == root){
            isRoot = true;
            isLeft = false;
            isRight = false;
        } else if (temp == temp.getParent().getLeftChild()){
            isRoot = false;
            isLeft = true;
            isRight = false;
        } else {
            isRoot = false;
            isLeft = false;
            isRight = true;
        }

        // Find out if the node to be deleted has children
        // If it does, find out if it has a Right/Left Child or both
        if (temp.getLeftChild() == null && temp.getRightChild() == null){
            hasChildren = false;
            hasBoth = false;
            hasLeft = false;
            hasRight = false;
        } else if (temp.getLeftChild() != null && temp.getRightChild() != null){
            hasChildren = true;
            hasBoth = true;
            hasLeft = true;
            hasRight = true;
        } else if (temp.getLeftChild() == null){
            hasChildren = true;
            hasBoth = false;
            hasRight = true;
            hasLeft = false;
        } else {
            hasChildren = true;
            hasBoth = false;
            hasRight = false;
            hasLeft = true;
        }

        // Seperate cases if the node to be deleted is the root of the tree
        if (isRoot){
            // Seperate cases depending on the number of children the node to be deleted has
            if (!hasChildren){
                root = replacement;
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                    root = replacement;
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                    root = replacement;
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(null);
                root = temp.getLeftChild();
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(null);
                root = temp.getRightChild();
            }
        } else {
            // This is the case where it isn't the root node
            if (!hasChildren){
                if (isLeft){
                    temp.getParent().setLeftChild(null);
                } else if (isRight){
                    temp.getParent().setRightChild(null);
                }
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                }
                if(isLeft){
                    temp.getParent().setLeftChild(replacement);
                } else if (isRight){
                    temp.getParent().setRightChild(replacement);
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(temp.getParent());
                temp.getParent().setLeftChild(temp.getLeftChild());
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(temp.getParent());
                temp.getParent().setRightChild(temp.getRightChild());
            }
        }
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    if (top.getData() != null) {
                        System.out.print(top.getData().toString() + " ");
                        traverse("preorder", top.getLeftChild());
                        traverse("preorder", top.getRightChild());
                    }
                    break;
                case "inorder":
                    if (top.getData() != null) {
                        traverse("inorder", top.getLeftChild());
                        System.out.print(top.getData().toString() + " ");
                        traverse("inorder", top.getRightChild());
                    }
                    break;
                case "postorder":
                    if (top.getData() != null) {
                        traverse("postorder", top.getLeftChild());
                        traverse("postorder", top.getRightChild());
                        System.out.print(top.getData().toString() + " ");
                    }
                    break;
            }
        }
    }

    public Node<E> getMin(Node<E> top){
        boolean done = false;
        Node<E> temp = top;
        while(!done) {
            if (temp.getLeftChild() == null) {
                done = true;
            } else {
                temp = temp.getLeftChild();
            }
        }
        return temp;
    }
}
