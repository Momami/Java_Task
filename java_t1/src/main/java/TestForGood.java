public class TestForGood {
    public static void main(String[] args) {
        Good good1 = new Good();
        System.out.println(good1.toString());
        good1.setHeight(3);
        good1.setLength(6);
        good1.setWidth(4.8);
        System.out.println(good1.toString());
        System.out.println(good1.squareWalls());
        Good good2 = new Good(7, 4.6, 2.5);
        System.out.println(good2.toString());
        System.out.println(good2.squareWalls());
        System.out.println(good2.squareWithoutDoor());
    }
}
