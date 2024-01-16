import lab3.*;
import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.exceptions.NegativeRadiusException;
import lab3.exceptions.NotInRadiusException;
import lab3.fight.FightImpact;
import lab3.fight.FightManager;
import lab3.items.Clothes;
import lab3.items.Cup;
import lab3.items.Spoon;
import lab3.items.Stick;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    // Статический вложенный класс
    final static class History {
        private static final ArrayList<String> data = new ArrayList<String>();

        public static void addMessage(String str) {
            data.add(str);
        }

        public static String getMessages() {
            String str = "";

            for (String el : data) {
                str += el + "\n";
            }

            return str;
        }

    }

    public static void main(String[] args) {
        Random randomizer = new Random();

        // clothes
        Clothes boots = new Clothes("Ботинки");
        Clothes trousers = new Clothes("Штаны");
        Clothes tshirt = new Clothes("Кофта");
        Clothes coat = new Clothes("Прорезиненный электрозащитный плащ");

        ArrayList<Clothes> vihorClothes = new ArrayList<>();
        vihorClothes.add(boots);
        vihorClothes.add(trousers);
        vihorClothes.add(tshirt);

        ArrayList<Clothes> kozlikClothes = new ArrayList<>();
        kozlikClothes.add(boots);
        kozlikClothes.add(trousers);
        kozlikClothes.add(tshirt);

        ArrayList<Clothes> strigaClothes = new ArrayList<>();
        strigaClothes.add(boots);
        strigaClothes.add(trousers);
        strigaClothes.add(tshirt);

        ArrayList<Clothes> siglClothes = new ArrayList<>();
        siglClothes.add(boots);
        siglClothes.add(trousers);
        siglClothes.add(tshirt);
        siglClothes.add(coat);

        ArrayList<Clothes> driglClothes = new ArrayList<>();
        driglClothes.add(boots);
        driglClothes.add(trousers);
        driglClothes.add(tshirt);
        driglClothes.add(coat);

        ArrayList<Clothes> zhmiglClothes = new ArrayList<>();
        zhmiglClothes.add(boots);
        zhmiglClothes.add(trousers);
        zhmiglClothes.add(tshirt);
        zhmiglClothes.add(coat);

        ArrayList<Clothes> phiglClothes = new ArrayList<>();
        phiglClothes.add(boots);
        phiglClothes.add(trousers);
        phiglClothes.add(tshirt);
        phiglClothes.add(coat);

        ArrayList<Clothes> stickShortyClothes = new ArrayList<>();
        stickShortyClothes.add(boots);
        stickShortyClothes.add(trousers);
        stickShortyClothes.add(tshirt);
        stickShortyClothes.add(coat);

        // cords
        Coordinate vihorCords = new Coordinate(0, 0);
        Coordinate kozlikCords = new Coordinate(0.5, 0.5);
        Coordinate strigaCords = new Coordinate(3.8, 4.3);
        Coordinate siglCords = new Coordinate(3.8, 4.1);
        Coordinate driglCords = new Coordinate(3.8, 4.1);
        Coordinate zhmiglCords = new Coordinate(3.8, 4.1);
        Coordinate phiglCords = new Coordinate(3.8, 4.1);
        Coordinate stickShortyCords = new Coordinate(3.8, 4.1);

        Coordinate spoonCords = new Coordinate(3.4, 2.1);
        Coordinate cupCords = new Coordinate(3.4, 2.1);

        // characters
        try {
            Shorty vihor = new Shorty(
                    vihorCords,
                    new RadiusCoordinateArea(2, vihorCords),
                    "Вихор",
                    vihorClothes
            );

            Shorty kozlik = new Shorty(
                    kozlikCords,
                    new RadiusCoordinateArea(2, kozlikCords),
                    "Козлик",
                    kozlikClothes
            );

            Shorty striga = new Shorty(
                    strigaCords,
                    new RadiusCoordinateArea(2, strigaCords),
                    "Стрига",
                    strigaClothes
            );

            Shorty sigl = new Shorty(
                    siglCords,
                    new RadiusCoordinateArea(2, siglCords),
                    "Сигль",
                    siglClothes
            );

            Shorty drigl = new Shorty(
                    driglCords,
                    new RadiusCoordinateArea(2, driglCords),
                    "Дригль",
                    driglClothes
            );

            Shorty zhmigl = new Shorty(
                    zhmiglCords,
                    new RadiusCoordinateArea(2, zhmiglCords),
                    "Жмигль",
                    zhmiglClothes
            );

            Shorty phigl = new Shorty(
                    phiglCords,
                    new RadiusCoordinateArea(2, phiglCords),
                    "Пхигль",
                    phiglClothes
            );

            Shorty stickShorty = new Shorty(
                    stickShortyCords,
                    new RadiusCoordinateArea(2, stickShortyCords),
                    "Коротышка",
                    stickShortyClothes
            );

            // items
            Spoon spoon = new Spoon(
                    "Ложка",
                    spoonCords,
                    new RadiusCoordinateArea(2, spoonCords)
            );

            Cup cup = new Cup(
                    "Кружка",
                    cupCords,
                    new RadiusCoordinateArea(2, cupCords)
            );

            Stick stick = new Stick(
                    "Дубинка",
                    stickShortyCords,
                    new RadiusCoordinateArea(0.5, stickShortyCords)
            );

            // anonymous class
            Stick electroStick = new Stick("Дубинка", siglCords, new RadiusCoordinateArea(0.5, siglCords)) {
                @Override
                public String interacted(Character obj) {
                    if (!this.area.compareWithCoordinate(obj.getCords())) {
                        throw new NotInRadiusException("Слишком далеко от объекта, чтобы воспользоваться им");
                    }
                    return obj.getName() + " воспользовался электрической дубинкой.";
                }
            };

            // fight
            ArrayList<Character> shortiesInArgument = new ArrayList<>();
            shortiesInArgument.add(vihor);
            shortiesInArgument.add(kozlik);
            FightManager fight = new FightManager(shortiesInArgument);

            // fight impacts
            FightImpact kozlikImpact = new FightImpact("Козлик нанес такой удар, что Вихор полетел в сторону", randomizer.nextInt(100));
            FightImpact vihorImpact = new FightImpact("Они вдвоем принялись тузить противника", randomizer.nextInt(100));
            FightImpact strigaImpact = new FightImpact("Они вдвоем принялись тузить противника", randomizer.nextInt(100));
            FightImpact siglImpact = new FightImpact("Сигль тыкал дерущихся электрическими дубинками", 2 * randomizer.nextInt(100));
            FightImpact driglImpact = new FightImpact("Дригль тыкал дерущихся электрическими дубинками", 2 * randomizer.nextInt(100));
            FightImpact zhmiglImpact = new FightImpact("Жмигль тыкал дерущихся электрическими дубинками", 2 * randomizer.nextInt(100));
            FightImpact phiglImpact = new FightImpact("Пхигль тыкал дерущихся электрическими дубинками", 2 * randomizer.nextInt(100));

            // main
            History.addMessage(fight.start());
            History.addMessage(kozlikImpact.getAbout());

            fight.addImpact(kozlik, kozlikImpact);

            History.addMessage("Стрига поспешил на помощь своему другу");
            fight.addParticipant(striga);

            History.addMessage(strigaImpact.getAbout());
            fight.addImpact(striga, strigaImpact);
            fight.addImpact(vihor, vihorImpact);

            History.addMessage("Они носили: " + vihor.getClothes());

            for (Character obj : fight.getParticipants()) {
                History.addMessage(obj.getName() + " визжал, стонал и крякал от ударов");
            }

            History.addMessage(stickShorty.getName() + " забрался на верхнюю полку");
            stickShorty.move(stickShorty.getCords().getCoordinates()[0], stickShorty.getCords().getCoordinates()[1] + 2);

            try {
                History.addMessage(stick.interacted(stickShorty) + " Колотил палкой всех, кто пробегал мимо");
            } catch (Exception e) {
                History.addMessage("Ошибка: " + e.getMessage());
            }

            History.addMessage(cup.thrown());
            History.addMessage(boots.thrown());
            History.addMessage(spoon.thrown());

            History.addMessage("Ворвались четверо полицейских: Дригль, Сигль, Жмигль и Пхигль.");
            fight.addParticipant(drigl);
            fight.addParticipant(sigl);
            fight.addParticipant(zhmigl);
            fight.addParticipant(phigl);

            History.addMessage("Они носили: " + drigl.getClothes());

            try {
                History.addMessage(electroStick.interacted(drigl));
                History.addMessage(electroStick.interacted(sigl));
                History.addMessage(electroStick.interacted(zhmigl));
                History.addMessage(electroStick.interacted(phigl));
                fight.addImpact(drigl, driglImpact);
                fight.addImpact(sigl, siglImpact);
                fight.addImpact(zhmigl, zhmiglImpact);
                fight.addImpact(phigl, phiglImpact);
            } catch (NotInRadiusException e) {
                History.addMessage("Ошибка: " + e.getMessage());
            }

            try {
                History.addMessage(fight.finish());
            } catch (NotInRadiusException e) {
                History.addMessage("Ошибка: " + e.getMessage());
            }

            History.addMessage("");
            History.addMessage("Участия в драке и их силы:");
            History.addMessage(fight.getStats().toString());

        } catch (NegativeRadiusException e) {
            History.addMessage("Фатальная ошибка: " + e.getMessage());
        } finally {
            System.out.print(History.getMessages());

        }
    }
}
