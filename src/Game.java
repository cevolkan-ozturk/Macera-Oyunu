import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    public void start() {
        System.out.println("Macera Oyununa Hosgeldiniz !");
        System.out.print("Lutfen bir oyuncu ismi giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayin " + player.getName() + " bu karanlik ve sisli adaya hosgeldiniz !! Burda yasananlarin hepsi gercek !");
        System.out.println("Lutfen bir karakter seciniz !");
        System.out.println("-----------------------------------------------------------------------------------");
        player.selectChar();


        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("########## Bolgeler #########");
            System.out.println();
            System.out.println("1 - Guvenli Ev --> Burasi sizin icin guvenli bir ev, dusman yoktur !  ");
            System.out.println("2 - Esya Dukkani --> Silah veya Zirh satin alabilirsiniz !");
            System.out.println("3 - Magara --> Odul <Yemek> , dikkatli ol zombi cikabilir !");
            System.out.println("4 - Orman --> Odul <Odun> , dikkatli ol vampir cikabilir !");
            System.out.println("5 - Nehir --> Odul <Su> , dikkatli ol ayi cikabilir !");
            System.out.println("0 - Cikis Yap --> Oyunu sonlandir. ");
            System.out.print("Lutfen gitmek istediginiz bolgeyi seciniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4 :
                    location = new Forrest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen gecerli bir bölge giriniz !");

            }

            if (location == null){
                System.out.println("Bu karanlık ve sisli adadan cabuk vazgeçtin !");
                break;
            }


            if(!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }


        }
        }
    }

