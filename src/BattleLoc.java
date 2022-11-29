import java.util.Random;

public abstract class BattleLoc extends Location {

    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monNumber = this.randomMonsterNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol! Burada " + monNumber + " tane " + this.getMonster().getName() + " yasiyor !");
        System.out.print("<S>avas veya <K>ac : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(monNumber)) {
            if (combat(monNumber)){
                System.out.println(this.getName() + " tüm düsmanlari yendiniz !");
                return true;
            }


        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Oldunuz !");
            return false;

        }
        return true;
}

public boolean combat(int monNumber){
        for ( int i=1 ; i <= monNumber;i++) {
            this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("<V>ur veya <K>ac : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz ! ");
                    this.getMonster().setHealth((this.monster.getHealth()-this.getPlayer().getTotalDamage()));
                    afterHit();
                    if(this.getMonster().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar Size Vurdu !");
                        int monsterDamage = (this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock());
                        if (monsterDamage < 0) {
                            monsterDamage = 0;
                        }

                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();
                    }
                }
                else {
                    return false;
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düsmani Yendiniz !");
                System.out.println(this.getMonster().getAward() + " para kazandiniz !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Guncel Paraniz: " + this.getPlayer().getMoney());

            }else{
                return false;
            }


        }
        return true;
}

public void afterHit() {
    System.out.println("Caniniz : " + this.getPlayer().getHealth());
    System.out.println(this.getMonster().getName() + " Cani :" + this.getMonster().getHealth());
    System.out.println("-------------------------");
}

public void playerStats() {
    System.out.println("Oyunucu Degerleri");
    System.out.println("------------------------");
    System.out.println("Oyuncu Adi: " + this.getPlayer().getName());
    System.out.println("Oyuncu Saglıgı: " + this.getPlayer().getHealth());
    System.out.println("Silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
    System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
    System.out.println("Zırhınız : " + this.getPlayer().getInventory().getArmor().getName());
    System.out.println("Bloklama :" + this.getPlayer().getInventory().getArmor().getBlock());
    System.out.println("Oyuncu Parasi : " + this.getPlayer().getMoney());
    System.out.println();
     }

     public void monsterStats(int i) {
         System.out.println(i + "." + this.getMonster().getName() + " Degerleri");
         System.out.println("--------------------");
         System.out.println("Canavar Sagligi : " + this.getMonster().getHealth());
         System.out.println("Canavar Hasari : " + this.getMonster().getDamage());
         System.out.println("Odul : " + this.getMonster().getAward());
     }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;

    }
    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
