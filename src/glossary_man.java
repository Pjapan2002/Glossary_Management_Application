import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class GlossaryProduct {

    private String productId;
    private String productName;
    private String productCatagerory;
    private String productQuantity;
    private String productPricePerPieces;

    public GlossaryProduct(String productId, String productName, String productCatagerory, String productQuantity, String productPricePerPieces) {
        this.productId = productId;
        this.productName = productName;
        this.productCatagerory = productCatagerory;
        this.productQuantity = productQuantity;
        this.productPricePerPieces = productPricePerPieces;
    }

    public String getproductId() {
        return productId;
    }

    public String getproductName() {
        return productName;
    }

    public String getproductCatagerory() {
        return productCatagerory;
    }

    public String getproductQuantity(){
        return productQuantity;
    }

    public String getproductPricePerPieces(){
        return productPricePerPieces;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public void setproductCatagerory(String productCatagerory) {
        this.productCatagerory = productCatagerory;
    }

    public void setproductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setproductPricePerPieces(String productPricePerPieces) {
        this.productPricePerPieces = productPricePerPieces;
    }

    @Override
    public String toString() {
        return "productId: " + productId + "\nproductName: " + productName + "\nproductCatagerory: " + productCatagerory + "\nproductQuantity: " + productQuantity + "\nproductPricePerPieces: " + productPricePerPieces + "\n";
    }
}

public class glossary_man {
    private static ArrayList<GlossaryProduct> glossary = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "glossary_data.txt";

    public static void main(String[] args) {
        loadGlossaryData(); // Load data from file (if available)

        while (true) {
            System.out.println("Glossary Management Application");
            System.out.println("1. View Glossary");
            System.out.println("2. Add product");
            System.out.println("3. Edit product");
            System.out.println("4. Delete product");
            System.out.println("5. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewGlossary();
                    break;
                case 2:
                    addproduct();
                    break;
                case 3:
                    editproduct();
                    break;
                case 4:
                    deleteproduct();
                    break;
                case 5:
                    saveGlossaryData(); // Save data to file
                    System.out.println("Data saved. Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewGlossary() {
        System.out.println("Glossary products:");
        for (GlossaryProduct productId : glossary) {
            System.out.println(productId);
        }
    }

    private static void addproduct() {
        System.out.print("Enter the productId: ");
        String productId = scanner.nextLine();
        System.out.print("Enter the productName: ");
        String productName = scanner.nextLine();
        System.out.print("Enter the productCatagerory: ");
        String productCatagerory = scanner.nextLine();
        System.out.print("Enter the productQuantity: ");
        String productQuantity = scanner.nextLine();
        System.out.print("Enter the productPricePerPieces: ");
        String productPricePerPieces = scanner.nextLine();

        GlossaryProduct newproduct = new GlossaryProduct(productId, productName, productCatagerory, productQuantity, productPricePerPieces);
        glossary.add(newproduct);

        System.out.println("product added successfully.");
    }

    private static void editproduct() {
        System.out.print("Enter the productId you want to edit: ");
        String searchproductId = scanner.nextLine();

        boolean found = false;
        for (GlossaryProduct productId : glossary) {
            if (productId.getproductId().equalsIgnoreCase(searchproductId)) {
                System.out.print("Enter the new productName: ");
                String newproductName = scanner.nextLine();
                productId.setproductName(newproductName);

                System.out.print("Enter the new productCatagerory: ");
                String newproductCatagerory = scanner.nextLine();
                productId.setproductCatagerory(newproductCatagerory);

                System.out.print("Enter the new productQuantity: ");
                String newproductQuantity = scanner.nextLine();
                productId.setproductQuantity(newproductQuantity);

                System.out.print("Enter the new productPricePerPieces: ");
                String newproductPricePerPieces = scanner.nextLine();
                productId.setproductPricePerPieces(newproductPricePerPieces);

                found = true;
                System.out.println("product updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("productId not found.");
        }
    }

    private static void deleteproduct() {
        System.out.print("Enter the productId you want to delete: ");
        String searchproductId = scanner.nextLine();

        GlossaryProduct productIdToRemove = null;
        for (GlossaryProduct productId : glossary) {
            if (productId.getproductId().equalsIgnoreCase(searchproductId)) {
                productIdToRemove = productId;
                break;
            }
        }

        if (productIdToRemove != null) {
            glossary.remove(productIdToRemove);
            System.out.println("productId deleted successfully.");
        } else {
            System.out.println("productId not found.");
        }
    }

    private static void loadGlossaryData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    GlossaryProduct product = new GlossaryProduct(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    glossary.add(product);
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveGlossaryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (GlossaryProduct product : glossary) {
                writer.write(
                        product.getproductId() + "," +
                                product.getproductName() + "," +
                                product.getproductCatagerory() + "," +
                                product.getproductQuantity() + "," +
                                product.getproductPricePerPieces()
                );
                writer.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
