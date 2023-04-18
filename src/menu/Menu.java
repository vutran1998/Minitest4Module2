package menu;

import contact.Contact;
import contact.Type;
import file.ReadFile;
import file.WriteFile;
import phonebookmanager.PhoneBookManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Serializable {
    private static final long serialVersionUID = 2906642554793891381L;

    public static ArrayList<Contact> listContact = new ArrayList<>();
    public static ArrayList<Type> listType = new ArrayList<>();
    static WriteFile writeFile = new WriteFile();
    static ReadFile readFile = new ReadFile();

    public static void addType() {
        listType = readFile.inputStream("C:\\module2\\Minitest4\\src\\file\\Type");
//        listType.add(new Type(1, "friend"));
//        listType.add(new Type(2, "family"));
//        listType.add(new Type(3, "work"));
    }

    public static void addContact() {
        listContact = readFile.inputStream("C:\\module2\\Minitest4\\src\\file\\Contact");
//        listContact.add(new Contact("tienanh", "12345", listType.get(0)));
//        listContact.add(new Contact("thuyhang", "56789", listType.get(1)));
//        listContact.add(new Contact("dongnam", "99999", listType.get(2)));
//        listContact.add(new Contact("son", "11335", listType.get(0)));
//        listContact.add(new Contact("ha", "55779", listType.get(1)));
//        listContact.add(new Contact("quan", "88888", listType.get(2)));
//        listContact.add(new Contact("son", "10101",null));
//        listContact.add(new Contact("dung", "13579",null));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBookManager phoneBookManager = new PhoneBookManager();
        addType();
        addContact();
        boolean check = true;
        int choice;
        do {
            System.out.println("Mời bạn lựa chọn ");
            System.out.println("1.Hiển thị danh sách");
            System.out.println("2.Tìm kiếm thông tin ");
            System.out.println("3.Thêm thông tin liên lạc");
            System.out.println("4.Sửa thông tin liên lạc");
            System.out.println("5.Xóa thông tin liên lạc");
            System.out.println("6.sắp xếp danh bạ");
            System.out.println("7.Thoát");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    int choiceC;
                    for (Type p : Menu.listType) {
                        System.out.println(p.getId() + "." + p.getTypeName());
                    }
                    System.out.println("Mời bạn nhập lựa chọn");
                    System.out.println("Ấn số 999 để hiện thị tất cả danh sách không thuộc loại nào ");
                    choiceC = Integer.parseInt(scanner.nextLine());
                    for (Type i : Menu.listType) {
                        if (i.getId() == choiceC) {
                            phoneBookManager.display(i);
                        } else if (choiceC==999){
                            phoneBookManager.display(null);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Mời bạn nhập tên");
                    String name = scanner.nextLine();
                    while (check) {
                        for (Contact p : Menu.listContact) {
                            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                                check = false;
                            }
                        }
                    }
                    phoneBookManager.searchPhone(name);
                    break;
                case 3:
                    System.out.println("Mời bạn nhập tên trong danh bạ");
                    String name1 = scanner.nextLine();
                    System.out.println("Nhập số điện thoại");
                    String phone = scanner.nextLine();
                    Contact contact1 = new Contact(name1, phone);
                    phoneBookManager.insertPhone(contact1);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Type", listType);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Contact", listContact);
                    break;
                case 4:
                    boolean check1 = true;
                    while (check1) {
                        System.out.println("Mời bạn nhập tên mà bạn muốn sửa");
                        String name2 = scanner.nextLine();
                        for (Contact p : Menu.listContact) {
                            if (p.getName().toLowerCase().equals(name2.toLowerCase())) {
                                System.out.println("Mời bạn nhập số điện thoại");
                                String phoneNum = scanner.nextLine();
                                phoneBookManager.updatePhone(name2, phoneNum);
                                check1 = false;
                            }
                        }
                    }
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Type", listType);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Contact", listContact);
                    break;
                case 5:
                    System.out.println("Mời bạn nhập tên liên lạc mà bạn muốn xóa");
                    String nameRemove = scanner.next();
                    phoneBookManager.removePhone(nameRemove);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Type", listType);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Contact", listContact);
                    break;
                case 6:
                    phoneBookManager.sort();
                    for (Contact p:Menu.listContact) {
                        System.out.println(p);
                    }
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Type", listType);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Contact", listContact);
                    break;
                case 7:
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Type", listType);
                    writeFile.outPutStream("C:\\module2\\Minitest4\\src\\file\\Contact", listContact);
                    break;

            }
        } while (choice != 7);

    }
}
