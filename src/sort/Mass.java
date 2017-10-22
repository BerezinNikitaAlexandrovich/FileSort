package sort;

// Класс работы с массивами. Содержит статические перегружаемые методы
public class Mass {

	/*
	 * метод сортировка методом вставки для массива типа long (целые числа)
	 * параметры: mass: сортируемый массив direction: направление сортировки
	 */
	public static void sort(long[] mass, String direction) {
		if (mass.length > 1) {
			/*
			 * если в массиве больше 1 элемента, начинаем процесс сортировки
			 */
			long temp;
			for (int i = 1; i < mass.length; i++) {
				temp = mass[i];
				// текущий элемент, начиная со второго в массиве

				int j = i;
				// запоминаем индекс текущего элемента

				for (; j > 0; j--) {
					// начинаем проход в обратную сторону по оставшейся части
					// массива

					if (direction.equals("-a")) {
						// сортировка по возрастанию

						if (mass[j - 1] < temp) {
							// если текущий элемент больше соседнего элемента в
							// оставшейся части
							// выходим из цикла
							break;
						}
					}
					if (direction.equals("-d")) {
						// сортировка по убыванию

						if (mass[j - 1] > temp) {
							// если текущий элемент меньше соседнего элемента в
							// оставшейся части
							// выходим из цикла
							break;
						}
					}
					mass[j] = mass[j - 1];
					// если условия не выполнились(например текущий элемент
					// меньше предыдущего при сортировке по возрастанию),
					// то "сдвигаем" бОльшие элементы оставшейся части массива
				}
				mass[j] = temp;
				// помещаем текущий элеменет в нужное (отсортированное) место
				// части массива
			}
		}
	}

	/*
	 * метод сортировка методом вставки для массива типа String (массив строк)
	 * параметры: mass: сортируемый массив direction: направление сортировки
	 */
	public static void sort(String[] mass, String direction) {
		if (mass.length > 1) {
			String temp;
			for (int i = 1; i < mass.length; i++) {
				temp = new String(mass[i]);
				int j = i;
				for (; j > 0; j--) {
					if (direction.equals("-a")) {
						if (mass[j - 1].compareTo(temp) < 0) {
							// метод compareTo - лексикографическое сравнение двух строк
							// метод класса String
							// основное отличие от метода sort для чисел типа long
							// где используются обычные операторы сравнения
							
							break;
						}
					}
					if (direction.equals("-d")) {
						if (mass[j - 1].compareTo(temp) > 0) {
							break;
						}
					}
					mass[j] = mass[j - 1];
				}
				mass[j] = temp;
			}
		}
	}

	// вывод массива в консоль. два перегружаемых метода, в программе не
	// используются
	public static void printMass(long[] mass) {
		for (int i = 0; i < mass.length; i++) {
			System.out.print(mass[i] + " ");
		}
		System.out.println();
	}

	public static void printMass(String[] mass) {
		for (int i = 0; i < mass.length; i++) {
			System.out.println(mass[i]);
		}
	}

}
