import lab3.*;
import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.exceptions.CustomCheckedException;
import lab3.exceptions.CustomUncheckedException;
import lab3.fight.FightImpact;
import lab3.fight.FightManager;
import lab3.items.Clothes;
import lab3.items.Cup;
import lab3.items.Spoon;
import lab3.items.Stick;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws CustomCheckedException {
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
                    throw new CustomUncheckedException("Слишком далеко от объекта, чтобы воспользоваться им");
                }
                return  obj.getName() + " воспользовался электрической дубинкой.";
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
        FightImpact siglImpact = new FightImpact("Сигль тыкал дерущихся электрическими дубинками", 2*randomizer.nextInt(100));
        FightImpact driglImpact = new FightImpact("Дригль тыкал дерущихся электрическими дубинками", 2*randomizer.nextInt(100));
        FightImpact zhmiglImpact = new FightImpact("Жмигль тыкал дерущихся электрическими дубинками", 2*randomizer.nextInt(100));
        FightImpact phiglImpact = new FightImpact("Пхигль тыкал дерущихся электрическими дубинками", 2*randomizer.nextInt(100));

        // main
        StringBuilder output = new StringBuilder();

        output.append(fight.start()).append("\n");
        output.append(kozlikImpact.getAbout()).append("\n");

        fight.addImpact(kozlik, kozlikImpact);

        output.append("Стрига поспешил на помощь своему другу").append("\n");
        fight.addParticipant(striga);

        output.append(strigaImpact.getAbout()).append("\n");
        fight.addImpact(striga, strigaImpact);
        fight.addImpact(vihor, vihorImpact);

        for (Character obj : fight.getParticipants()) {
            output.append(obj.getName()).append(" визжал, стонал и крякал от ударов").append("\n");
        }

        output.append(stickShorty.getName()).append(" забрался на верхнюю полку").append("\n");
        stickShorty.move(stickShorty.getCords().getCoordinates()[0], stickShorty.getCords().getCoordinates()[1] + 2);

        try {
            output.append(stick.interacted(stickShorty)).append(" Колотил палкой всех, кто пробегал мимо").append("\n");
        } catch (CustomUncheckedException e) {
            output.append("Ошибка: ").append(e.getMessage()).append("\n");
        }

        output.append(cup.thrown()).append("\n");
        output.append(boots.thrown()).append("\n");
        output.append(spoon.thrown()).append("\n");

        output.append("Ворвались четверо полицейских: Дригль, Сигль, Жмигль и Пхигль.").append("\n");
        fight.addParticipant(drigl);
        fight.addParticipant(sigl);
        fight.addParticipant(zhmigl);
        fight.addParticipant(phigl);

        output.append("Они носили: ").append(drigl.getClothes()).append("\n");

        try {
            output.append(electroStick.interacted(drigl)).append("\n");
            output.append(electroStick.interacted(sigl)).append("\n");
            output.append(electroStick.interacted(zhmigl)).append("\n");
            output.append(electroStick.interacted(phigl)).append("\n");
            fight.addImpact(drigl, driglImpact);
            fight.addImpact(sigl, siglImpact);
            fight.addImpact(zhmigl, zhmiglImpact);
            fight.addImpact(phigl, phiglImpact);
        } catch (CustomUncheckedException e) {
            output.append("Ошибка: ").append(e.getMessage()).append("\n");
        }

        try {
            output.append(fight.finish()).append("\n");
        } catch (CustomUncheckedException e) {
            output.append("Ошибка: ").append(e.getMessage());
        }

        System.out.print(output);
    }
}

// todo lambda, func interfaces, annotations, dto(done)