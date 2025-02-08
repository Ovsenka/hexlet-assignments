package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String source1, String source2, String target)
            throws ExecutionException, InterruptedException {
        Path sourcePath1 = Paths.get(source1).toAbsolutePath().normalize();
        Path sourcePath2 = Paths.get(source2).toAbsolutePath().normalize();
        Path targetPath = Paths.get(target).toAbsolutePath().normalize();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String result = "";
            try {
                result = Files.readString(sourcePath1).replaceAll("\\n", "");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return result;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            String result = "";
            try {
                result = Files.readString(sourcePath2).replaceAll("\\n", "");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return result;
        });

        return future1.thenCombine(future2, (cont1, cont2) -> {
            String concated = String.join(" ", cont1, cont2);
            try {
                Files.writeString(targetPath, concated);
                return concated;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String source1 = "src/main/resources/file1.txt";
        String source2 = "src/main/resources/file2.txt";
        String target = "src/main/resources/target.txt";
        CompletableFuture<String> result = unionFiles(source1, source2, target);
        System.out.println(result.get());
        System.out.println(getDirectorySize("src/main/resources/").get());
        // END
    }

    public static CompletableFuture<Long> getDirectorySize(String dirAddress) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(dirAddress).toAbsolutePath().normalize();
                long resultSize = 0;
                var dirStream = Files.newDirectoryStream(path);
                for (Path p : dirStream) {
                    long tempSize = Files.size(p);
                    resultSize += tempSize;
                }
                return resultSize;
            } catch (Exception ex) {
                throw new RuntimeException("Ошибка при чтении директории " + ex.getMessage());
            }
        });
    }
}

