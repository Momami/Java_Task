public class TestForBook {
    public static void main(String[] args) {
        Book book1 = new Book();
        System.out.println(book1.toString());
        book1.setCountPage(200);
        book1.setName("Чужак");
        book1.setPrice(456);
        System.out.println(book1.toString());
        System.out.println(book1.averagePricePage());
        Book book2 = new Book("Граф Монте-Кристо", 457, 555, 1980);
        System.out.println(book2.countDayAfterYearOfBirth());
        Book book3 = new Book("Сияние", 457, 555, 1981);
        System.out.println(book3.countDayAfterYearOfBirth());
        Book book4 = new Book("Азбука", 457, 555, 1979);
        System.out.println(book4.countDayAfterYearOfBirth());
    }
}
