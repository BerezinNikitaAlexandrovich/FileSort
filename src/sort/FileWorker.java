package sort;

// Класс работы с файлами. Содержит статические методы для получения/записи данных из файла
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker {

	/*
	 * перегружаемый метод получения массива типа long (целые числа) из файла
	 * параметры: inputFileName - имя входного файла с данными
	 */

	public static long[] longMassFromFile(String inputFileName) {
		File file = new File(inputFileName);
		// загружаем файл
		
		int size = CheckingData.checkAndGetAmountOfLines(file);
		// получаем количество строк в файле
		
		if (size <= 0) {
			// если размер меньше либо равен 0 - прекращаем выполнение метода и возвращаем пустую ссылку
			return null;
		}
		try {
			// используем объект класса Scanner чобы сразу считывать числа
			Scanner sc = new Scanner(file);
			long[] mass = new long[size];
			for (int i = 0; i < size; i++) {
				mass[i] = sc.nextLong();
				// считываем в созданный массив числа
			}
			sc.close();
			return mass;
		} catch (Exception e) {
			// если в процессе считывания произошла ошибка 
			// (файл содержит строки, а считываем его как файл с числами)
			// создаем файл где выводим сообщение об ошибке
			
			File errorLog = new File("errorlog.txt");
			try {
				FileWriter errorLogWriter = new FileWriter(errorLog);
				errorLogWriter
						.write("введены неправильные числовые значения/ошибка работы с файлом"
								+ "\r\n");
				errorLogWriter.close();
			} catch (IOException e1) {
				return null;
			}
			return null;
		}

	}

	/*
	 * перегружаемый метод получения массива типа String(массив строк) из файла
	 * параметры: inputFileName - имя входного файла с данными
	 */

	public static String[] stringMassFromFile(String inputFileName) {
		File file = new File(inputFileName);
		int size = CheckingData.checkAndGetAmountOfLines(file);
		if (size <= 0) {
			return null;
		}
		try {
			Scanner sc = new Scanner(file);
			String[] mass = new String[size];
			for (int i = 0; i < size; i++) {
				mass[i] = sc.nextLine();
				// отличие от предыдущего метода - использование метода nextLine, считывающего строку целиком
				
			}
			sc.close();
			return mass;
		} catch (FileNotFoundException e) {
			File errorLog = new File("errorlog.txt");
			try {
				FileWriter errorLogWriter = new FileWriter(errorLog);
				errorLogWriter.write("ошибка работы с файлом" + "\r\n");
				errorLogWriter.close();
			} catch (IOException e1) {
				return null;
			}
			return null;
		}

	}

	/*
	 * перегружаемый метод вывода массива с целыми числами в файл
	 * параметры: outputFileName - имя выходного файла с результатом
	 * mass - выгружаемый в файл массив
	 */
	public static void massToFile(String outputFileName, long[] mass) {
		File file = new File(outputFileName);
		try {
			FileWriter fileWriter = new FileWriter(file);
			for (long l : mass) {
				fileWriter.write(String.valueOf(l) + "\r\n");
			}
			fileWriter.close();
		} catch (IOException e) {

		}
	}

	/*
	 * перегружаемый метод вывода массива со строками в файл
	 * параметры: outputFileName - имя выходного файла с результатом
	 * mass - выгружаемый в файл массив
	 */
	public static void massToFile(String outputFileName, String[] mass) {
		File file = new File(outputFileName);
		try {
			FileWriter fileWriter = new FileWriter(file);

			for (String str : mass) {
				fileWriter.write(str + "\r\n");
			}
			fileWriter.close();
		} catch (IOException e) {
		}
	}

	/*
	 * метод запуска процесса загрузки из файла , сортировки и вывода в файл
	 * массива строк или целых чисел параметры - массив строк из имен
	 * входного/выходного файлов, тип обрабатываемых данных, направление
	 * сортировки
	 */
	public static void startSort(String[] args) {

		if (CheckingData.checkArgs(args)) {
			// проверка входных данных на наличие ошибок

			if (args[2].equals("-i")) {
				// если работаем с целыми числами

				long[] mass;
				mass = longMassFromFile(args[0]);
				// получаем массив из файла
				
				Mass.sort(mass, args[3]);
				// сортируем массив в нужном направлении
				
				massToFile(args[1], mass);
				// выводим отсортированный массив в файл
				
			} else if (args[2].equals("-s")) {
				// если работаем с массивом строк
				// выполняем тоже самое
				
				String[] mass;
				mass = stringMassFromFile(args[0]);
				Mass.sort(mass, args[3]);
				massToFile(args[1], mass);
			}

		}
	}
}
