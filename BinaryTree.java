package lab8;

import java.util.ArrayList;

public class BinaryTree {
	private Node root = null;

	public void add(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			addTo(root, value);
		}
	}

	Node findNode(int value) {
		return containsNode(root, value);
	}

	private void addTo(Node node, int value) {

		Node find = findNode(value);

		if (find != null) {
			System.out.println("Нельзя добавить данное значение, т.к. оно существует");
			return;
		}
		if (value < node.getKey()) {
			if (node.getLeft() == null) {
				Node temp = new Node(value);
				node.setLeft(temp);
				node.getLeft().setParent(node);
			} else
				addTo(node.getLeft(), value);
		} else if (node.getRight() == null) {
			Node temp = new Node(value);
			node.setRight(temp);
			node.getRight().setParent(node);
		} else
			addTo(node.getRight(), value);
	}

	private Node containsNode(Node node, int value) {
		if (node == null)
			return null;
		else if (node.getKey() == value)
			return node;
		if (value < node.getKey())
			return containsNode(node.getLeft(), value);
		else
			return containsNode(node.getRight(), value);
	}

	private void treeDirect(Node node) {
		if (node != null) {
			System.out.print(node.getKey() + " ");
			treeDirect(node.getLeft());
			treeDirect(node.getRight());
		}
	}

	private void treeReverse(Node node) {
		if (node != null) {
			treeReverse(node.getLeft());
			treeReverse(node.getRight());
			System.out.print(node.getKey() + " ");
		}
	}

	private void treeSymmetrical(Node node) {
		if (node != null) {
			treeSymmetrical(node.getLeft());
			System.out.print(node.getKey() + " ");
			treeSymmetrical(node.getRight());
		}
	}

	public void direct() {
		treeDirect(root);
		System.out.println();
	}

	public void reverse() {
		treeReverse(root);
		System.out.println();
	}

	public void symmetrical() {
		treeSymmetrical(root);
		System.out.println();
	}

	public int height(int value) {
		if (root == null) {
			System.out.println("Дерево пусто!");
			return -1;
		} else {
			Node node = findNode(value);
			if (node == null) {
				System.out.println("Такого узла нет");
				return -1;
			} else {
				int count = treeHeight(node);
				return count;
			}
		}
	}

	public int depth(int value) {
		if (root == null) {
			System.out.println("Дерево пусто!");
			return -1;
		}
		Node node = findNode(value);
		if (node == null) {
			System.out.println("Не существует такого элемента!");
			return -1;
		} else {
			int count = treeDepth(node);
			return count;
		}
	}

	public int level(int value) {
		if (root == null) {
			System.out.println("Дерево пусто!!");
			return -1;
		}
		Node node = findNode(value);
		if (node == null) {
			System.out.println("Не существует такого элемента!");
			return -1;
		} else {
			int count = treeHeight(root) - treeDepth(node);
			return count;
		}
	}

	private int treeDepth(Node node) {
		if (node.getParent() == null)
			return 0;
		return 1 + treeDepth(node.getParent());
	}

	private int treeHeight(Node node) {
		int x = 0, y = 0;
		if (node == null)
			return 0;
		if (node.getLeft() != null)
			x = 1 + treeHeight(node.getLeft());
		if (node.getRight() != null)
			y = 1 + treeHeight(node.getRight());
		if (x > y)
			return x;
		else
			return y;
	}

	public void print() {
		treePrint("", root, false, false, 1);
	}

	public void task() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root != null) {
			list.add(root.getKey());
			max(root, list);
			System.out.println(list.toString());

			if (list.size() == 1) {
				System.out.println("В дереве существует только корень!");
				return;
			}
			int index = list.size() / 2;

			deleteRight(list.get(index));/// удаление серединки
			deleteRight(list.get(0));/// удаление корня
			System.out.println("Прямой обход дерева:");
			direct();
			System.out.println("Вывод:");
			print();
		}
	}

	private void max(Node node, ArrayList<Integer> list) {
		int l = 0;
		int r = 0;
		if (node.getLeft() == null && node.getRight() == null)
			return;
		else {
			if (node.getLeft() != null)
				l = treeHeight(node.getLeft());
			if (node.getRight() != null)
				r = treeHeight(node.getRight());
		}

		if (l > r) {
			list.add(node.getLeft().getKey());
			max(node.getLeft(), list);
		}
		if (r >= l) {
			if (node.getRight() != null) {
				list.add(node.getRight().getKey());
				max(node.getRight(), list);
			} else {
				list.add(node.getLeft().getKey());
				max(node.getLeft(), list);
			}
		}
	}

	private void treePrint(String s, Node node, boolean lefts, boolean temp, int l) {
		if (node != null) {
			if (node.getLeft() != null || node.getRight() != null)
				temp = true;
			else
				temp = false;
			System.out.print(s);
			if (lefts)
				System.out.println("|_L:" + node.getKey());
			else {
				if (l == 1) {
					System.out.println("-" + node.getKey());
					l++;
				} else
					System.out.println("|_R:" + node.getKey());
			}
			if (lefts) {
				treePrint(s + "|", node.getLeft(), true, temp, l);
				treePrint(s + "|", node.getRight(), false, temp, l);
			} else {
				treePrint(s + " ", node.getLeft(), true, temp, l);
				treePrint(s + " ", node.getRight(), false, temp, l);
			}
		} else {
			if (temp) {
				System.out.print(s);
				if (lefts)
					System.out.println("|_L:-");
				else
					System.out.println("|_R:-");
			}
		}
	}

	public void deleteRight(int value) {
		Node node = findNode(value);
		if (node == null) {
			System.out.println("Такого  элемента нет!!!");
			return;
		}
		if (delete(node))// проверяем является ли это висящей вершиной, если так, то просто удаляем
			return;
		if (node.getRight() == null) {
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(node.getLeft());
			else
				node.getParent().setRight(node.getLeft());

			node.getLeft().setParent(node.getParent());
			
			node = null;
		} else {
			treeDeleteRight(node);
		}
		System.out.println("Элемент удален!");
	}

	private void treeDeleteRight(Node node) {
		Node temp = node.getRight();
		while (temp.getLeft() != null)
			temp = temp.getLeft();

		if (temp.getParent() != node) {
			if (temp.getRight() != null) {
				temp.getParent().setLeft(temp.getRight());

				temp.getRight().setParent(temp.getParent());
			}

			temp.setRight(node.getRight());
			node.getRight().setParent(temp);
			temp.getParent().setLeft(null);
		}

		temp.setLeft(node.getLeft());
		node.getLeft().setParent(temp);

		if (node.getParent() != null) {
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(temp);
			else
				node.getParent().setRight(temp);
		}

		temp.setParent(node.getParent());

		if (node == root) {
			root = null;
			root = temp;
		} else {
			node = null;
			node = temp;
		}
	}

	public void deleteLeft(int value) {
		Node node = findNode(value);
		if (node == null) {
			System.out.println("Такой элемент не найден!!!!");
		}
		if (delete(node))
			return;
		if (node.getLeft() == null) {
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(node.getRight());
			else
				node.getParent().setRight(node.getRight());

			node.getRight().setParent(node.getParent());
			node = null;
		} else {
			treeDeleteLeft(node);
		}
		System.out.println("Элемент удален!");
	}

	private void treeDeleteLeft(Node node) {
		Node temp = node.getLeft();
		while (temp.getRight() != null)
			temp = temp.getRight();

		if (temp.getParent() != node) {
			if (temp.getLeft() != null) {
				temp.getParent().setRight(temp.getRight());

				temp.getLeft().setParent(temp.getParent());
			}

			temp.setLeft(node.getLeft());
			node.getLeft().setParent(temp);
			temp.getParent().setRight(null);
		}

		temp.setRight(node.getRight());
		node.getRight().setParent(temp);

		if (node.getParent() != null) {
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(temp);
			else
				node.getParent().setRight(temp);
		}

		temp.setParent(node.getParent());

		if (node == root) {
			root = null;
			root = temp;
		} else {
			node = null;
			node = temp;
		}
	}

	private boolean delete(Node node) {
		if (node != null) {
			if (node.getLeft() == null && node.getRight() == null) {
				if (node != root)
					if (node.getParent().getLeft() == node)
						node.getParent().setLeft(null);
					else
						node.getParent().setRight(null);
				if (node == root) {
					root = null;
					node = null;
				}
				System.out.println("Элемент удален!");
				return true;
			}
		}
		return false;
	}

}
