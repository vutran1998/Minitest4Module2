package phonebookmanager;

import contact.Contact;
import contact.Type;
import iphone.IPhone;
import menu.Menu;
import phone.Phone;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class PhoneBookManager extends Phone implements IPhone, Serializable {
    private static final long serialVersionUID = 2906642554793841381L;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void searchPhone(String name) {
        for (Contact p : Menu.listContact) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                System.out.println(p);
            }
        }
    }

    @Override
    public void sort() {
        Collections.sort(Menu.listContact);
    }

    @Override
    public void display(Type type) {
        if (Objects.isNull(type)) {
            for (Contact p : Menu.listContact) {
                    System.out.println(p);
            }
        } else {
            for (Contact p : Menu.listContact) {
                if (type.getTypeName().equals(p.getType().getTypeName())) {
                    System.out.println(p);
                }
            }
        }
    }

    @Override
    public void insertPhone(Contact contact) {
        int countId = 1;
        String name;
        int choice;
        int choice1;
        for (int i = 0; i < Menu.listType.size(); i++) {
            countId++;
        }
        do {
            System.out.println("Bạn muốn thay đổi danh bạ ?");
            System.out.println("1.Thêm type mới");
            System.out.println("2.Lựa chọn type có sẵn");
            System.out.println("3.Thoát");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Type type;
                    System.out.println("Mời bạn nhập kiểu của type");
                    name = scanner.nextLine();
                    type = new Type(countId, name);
                    Menu.listType.add(type);
                    for (Contact p : Menu.listContact) {
                        if (contact.getName().equals(p.getName())) {
                            p.setType(type);
                            Menu.listContact.add(p);
                            System.out.println("Thêm liên lạc thành công ");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Mời bạn lựa chọn type ");
                    for (Type p : Menu.listType) {
                        System.out.println(p.getId() + "." + p.getTypeName());
                    }
                    choice1 = Integer.parseInt(scanner.nextLine());
                    for (Type x : Menu.listType) {
                        if (x.getId() == choice1) {
                            Menu.listContact.add(new Contact(contact.name, contact.phoneNumber,x));
                        }
                    }
            }

        } while (choice != 3);
    }

    @Override
    public void removePhone(String name) {
        for (Contact p : Menu.listContact) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                Menu.listContact.remove(p);
            }
        }
    }

    @Override
    public void updatePhone(String name, String newPhone) {
        for (Contact p : Menu.listContact) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                p.setPhoneNumber(newPhone);
            }
        }
    }
}
