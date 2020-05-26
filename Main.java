///https://github.com/Julia20011974/AaSD

package lab8;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		BinaryTree bTree = new BinaryTree();
		menu(bTree);
	}

	static void menu(BinaryTree bTree) {
		Scanner in = new Scanner(System.in);
		String choice = "";
		while (!choice.equals("14")) {
			System.out.println("1-Добавить элемент в дерево\n" + 
					"2 - поиск элемента\n" + "3 - правое удаление\n"
					+ "4 - левое удаление\n" + "5 - прямой обход\n" + "6 - обратный обход\n"
					+ "7 - симметричный обход\n" + "8 - глубина\n" + "9 - высота\n" + "10 - уровень узла\n"
					+ "11 - Среди максимальных путей в дереве выбрать тот,\n"
					+ "у которого сумма ключей всех его вершин максимальна,\n"
					+ "затем удалить центральную вершину и корень этого пути (правым удалением).\n"
					+ "Выполнить прямой (левый) обход полученного дерева.\n" + 
					"12 - страдальное отображение дерева\n"+
					"13 - заполнить дерево тестовыми значениями\n"+
					"14 - выход");
			choice = in.next();
			switch (choice) {
			case "1": {
				System.out.print("Введите элемент: ");
				int value = in.nextInt();
				bTree.add(value);
				break;
			}
			case "2": {
				System.out.print("Введите элемент, который нужно найти: ");
				int value = in.nextInt();
				if (bTree.findNode(value) != null)
					System.out.println("Такой элемент действительно найден!");
				else
					System.out.println("Элемент не найден!");
				break;
			}
			case "3":{
				System.out.print("Введите какое значение нужно удалить:");
				int value = in.nextInt();
				bTree.deleteRight(value);
				break;
			}
			case "4":{
				System.out.print("Введите какое значение нужно удалить:");
				int value = in.nextInt();
				bTree.deleteLeft(value);
				break;
			}
			case "5": {
				System.out.println("Прямой обход дерева:");
				bTree.direct();
				break;
			}
			case "6": {
				System.out.println("Обратный обход дерева:");
				bTree.reverse();
				break;
			}
			case "7": {
				System.out.println("Симметричный обход дерева:");
				bTree.symmetrical();
				break;
			}
			case "8": {
				System.out.println("Введите значение узла:");
				int value = in.nextInt();
				System.out.println("Глубина:");
				int depth = bTree.depth(value);
				if(depth!=-1)
					System.out.println(depth);
				break;
			}
			case "9":
			{
				System.out.println("Введите значение узла:");
				int value = in.nextInt();
				int height = bTree.height(value);
				System.out.println("Высота:");
				if(height!=-1)
					System.out.println(height);
				break;
			}
			case "10":{
				System.out.println("Введите значение узла:");
				int value = in.nextInt();
				int level = bTree.level(value);
				System.out.println("Уровень:");
				if(level!=-1)
					System.out.println(level);
				break;
			}
			case "11":
				bTree.task();
				break;
			case "12":
				bTree.print();
				break;
			case "13":{
				bTree.add(7);
				bTree.add(3);
				bTree.add(2);
				bTree.add(1);
				bTree.add(5);
				bTree.add(4);
				bTree.add(6);
				bTree.add(9);
				bTree.add(8);
				/*/// можно заблокировать верхнюючасть и разблокировать нижнюю для тестовых значений
				bTree.add(8);
				bTree.add(4);
				bTree.add(12);
				bTree.add(2);
				bTree.add(6);
				bTree.add(1);
				bTree.add(5);
				bTree.add(3);
				bTree.add(7);
				bTree.add(10);
				bTree.add(9);
				bTree.add(11);
				bTree.add(14);
				bTree.add(13);
				bTree.add(15);*/
				break;
			}
			}
		}
		in.close();
	}
}
