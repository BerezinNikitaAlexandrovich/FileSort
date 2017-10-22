package sort;

// Класс содержит статические методы, проверяющие входные данные на наличие ошибок
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class CheckingData {
	private static String forbiddenChars = "*|\\\"<>?/:";

	// запрещенные символы для использования в имени файла

	/*
	 * метод проверки типов данных(сортируются строки или числа) параметр:
	 * dataType: тип данных
	 */
	private static boolean checkDataType(String dataType) {
		if (dataType.equals("-i") || dataType.equals("-s")) {
			return true;
		}
		return false;
	}

	/*
	 * метод проверки направления сортировки параметр: direction: направление
	 */
	private static boolean checkDirectionType(String direction) {
		if (direction.equals("-a") || direction.equals("-d")) {
			return true;
		}
		return false;
	}

	/*
	 * метод проверки вводимых данных на наличие ошибок, наличе входного файла
	 */
	public static boolean checkArgs(String[] args) {

		try {
			File errorLog = new File("errorlog.txt");
			FileWriter errorLogWriter = new FileWriter(errorLog);
			// создаем файл-лог ошибок, куда будет записываться ошибка
			// остановившая программу

			if (args.length != 4) {
				// если неверное количество аргументов в командной строке

				errorLogWriter.write("неверное количество аргументов" + "\r\n");
				errorLogWriter.close();
				return false;
			}
			if (!chekFileName(args[0]) || !chekFileName(args[1])
					|| args[0].equals(args[1])) {
				// если синтаксическая ошибка в именах входного/выходного файлов 
				
				errorLogWriter.write("неверное имя файла" + "\r\n");
				errorLogWriter.close();
				return false;
			}
			
			File inputFile = new File(args[0]);
			if (!inputFile.exists()) {
				// если указанный входной файл не существует
				
				errorLogWriter.write("входной файл не существует" + "\r\n");
				errorLogWriter.close();
				return false;
			}
			if (!checkDataType(args[2]) || !checkDirectionType(args[3])) {
				// если ошибки в аргументах тип данных и направление сортировки
				
				System.out.println(checkDirectionType(args[2]));
				errorLogWriter.write("неверные параметры сортировки и типа данных"
						+ "\r\n");
				errorLogWriter.close();
				return false;
			}
			
			// если все нормально, в лог ошибок выводим сообщение
			errorLogWriter.write("good job");
			errorLogWriter.close();
		} catch (IOException e) {

			return false;
		}
		return true;
	}

	/*
	 * метод проверки имени файла на правильность написания и размер названия
	 * параметр: str: имя файла
	 */
	private static boolean chekFileName(String str) {
		int pointCounter = 0;
		// счетчик точек

		if (str.length() > 20) {
			// если длинна больше 20 символов, возвращаем ошибку

			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (forbiddenChars.contains(String.valueOf(str.charAt(i)))) {
				// если в проверяемом имени есть запрещенные символы, возвращаем
				// ошибку

				return false;
			}
			if (str.charAt(i) == '.') {
				// считаем точки в имени файла

				pointCounter++;
			}
		}
		if (pointCounter != 1) {
			// если точек не одна, возвращаем ошибку

			return false;
		}
		if (!str.substring(str.lastIndexOf(".") + 1).equals("txt")) {
			// если расширение не txt, возвращаем ошибку

			return false;
		}

		// при успешно выполненой проверке
		return true;
	}

	/*
	 * метод проверки и получения количества строк в файле параметр: file:
	 * проверяемый файл
	 */
	public static int checkAndGetAmountOfLines(File file) {

		int lineNumber = 0;
		// счетчик строк

		try {
			FileReader fileReader = new FileReader(file);
			LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
			while (lineNumberReader.readLine() != null) {
				// пока есть строки, увеличиваем счетчик
				lineNumber++;

				if (lineNumber > 100) {
					// если количество строк стало больше 100, сообщаем об этом
					// в файл-лог ошибок
					File errorLog = new File("errorlog.txt");
					FileWriter errorLogWriter = new FileWriter(errorLog);
					errorLogWriter
							.write("количество строк больше 100" + "\r\n");
					errorLogWriter.close();
					lineNumberReader.close();

					// возвращаем при этом 0
					return 0;
				}
			}
			lineNumberReader.close();
		} catch (Exception e) {
			// при других ошибках работы с файлом возвращаем 0
			return 0;
		}

		return lineNumber;
	}

}
