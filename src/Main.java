import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static final StringBuilder LOG = new StringBuilder();

    public static void main(String[] args) {

        // В папке Games создайте несколько директорий: src, res, savegames, temp.
        makeDir(new File("D://Games//src"));
        makeDir(new File("D://Games//res"));
        makeDir(new File("D://Games//savegames"));
        makeDir(new File("D://Games//temp"));

        // В каталоге src создайте две директории: main, test.
        makeDir(new File("D://Games//src//main"));
        makeDir(new File("D://Games//src//test"));

        // В подкаталоге main создайте два файла: Main.java, Utils.java.
        makeFile(new File("D://Games//src//main", "Main.java"));
        makeFile(new File("D://Games//src//main", "Utils.java"));

        // В каталог res создайте три директории: drawables, vectors, icons.
        makeDir(new File("D://Games//res//drawables"));
        makeDir(new File("D://Games//res//vectors"));
        makeDir(new File("D://Games//res//icons"));

        // В директории temp создайте файл temp.txt.
        File temp = new File("D://Games//temp", "temp.txt");
        makeFile(temp);

        try (FileWriter writer = new FileWriter("D://Games//temp//temp.txt", true)) {
            writer.write(LOG.toString());
            writer.append('\n');
            writer.flush();
            System.out.println("Установка завершена. Лог в 'temp.txt'.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void makeDir(File newdir) {
        if (newdir.mkdir())
            LOG.append("\n[").append(LocalDateTime.now()).append("] <Создана папка> ").append(newdir);
        else if (newdir.exists())
            LOG.append("\n[").append(LocalDateTime.now()).append("] <Уже существует папка> ").append(newdir);
        else
            LOG.append("\n[").append(LocalDateTime.now()).append("] <Ошибка создания папки> ").append(newdir);
    }

    public static void makeFile(File newfile) {
        try {
            if (newfile.createNewFile())
                LOG.append("\n[").append(LocalDateTime.now()).append("] <Создан файл> ").append(newfile);
            else if (newfile.exists())
                LOG.append("\n[").append(LocalDateTime.now()).append("] <Уже существует файл> ").append(newfile);
        } catch (IOException ex) {
            LOG.append("\n[").append(LocalDateTime.now()).append("] <Ошибка создания файла> ").append(newfile);
            throw new RuntimeException(ex);
        }
    }
}