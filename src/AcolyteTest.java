import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class AcolyteTest{

    public static Statement stat;
    public static ResultSet resSet;
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
            System.out.println("Connected");
            stat = connection.createStatement();
            stat.execute("CREATE TABLE if not exists 'types' ('id' INTEGER PRIMARY KEY AUTOINCREMENT , 'type' text NOT NULL);");
            stat.execute("CREATE TABLE if not exists 'cats' ('id' INTEGER PRIMARY KEY NOT NULL ,'type_id' INTEGER NOT NULL  , 'name' text NOT NULL, 'age' INTEGER , 'weight' double NOT NULL, FOREIGN KEY (type_id) REFERENCES types(id));");









            } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        add_more_cats(5000);



    }
    static String[] types = new String[]{"Абиссинская кошка",
            "Австралийский мист",
            "Американская жесткошерстная",
            "Американская короткошерстная",
            "Американский бобтейл",
            "Американский кёрл",
            "Балинезийская кошка",
            "Бенгальская кошка",
            "Бирманская кошка",
            "Бомбейская кошка",
            "Бразильская короткошёрстная",
            "Британская длинношерстная",
            "Британская короткошерстная",
            "Бурманская кошка",
            "Бурмилла кошка",
            "Гавана",
            "Гималайская кошка",
            "Девон-рекс",
            "Донской сфинкс",
            "Европейская короткошерстная",
            "Египетская мау",
            "Канадский сфинкс",
            "Кимрик",
            "Корат",
            "Корниш-рекс",
            "Курильский бобтейл",
            "Лаперм",
            "Манчкин",
            "Мейн-ку́н",
            "Меконгский бобтейл",
            "Мэнкс кошка",
            "Наполеон",
            "Немецкий рекс",
            "Нибелунг",
            "Норвежская лесная кошка",
            "Ориентальная кошка",
            "Оцикет",
            "Персидская кошка",
            "Петерболд",
            "Пиксибоб",
            "Рагамаффин",
            "Русская голубая кошка",
            "Рэгдолл",
            "Саванна",
            "Селкирк-рекс",
            "Сиамская кошка",
            "Сибирская кошка",
            "Сингапурская кошка",
            "Скоттиш-фолд",
            "Сноу-шу",
            "Сомалийская кошка",
            "Тайская кошка",
            "Тойгер",
            "Тонкинская кошка",
            "Турецкая ангорская кошка",
            "Турецкий ван",
            "Украинский левкой",
            "Чаузи",
            "Шартрез",
            "Экзотическая короткошерстная",
            "Японский бобтейл"
    };
        public static void addType(String type) throws SQLException {
          stat.execute("INSERT INTO 'types' ('type') VALUES ('"+type+"');");
        }
        public static void addAll(String[] all ) throws SQLException {
            for(int i=0;i<types.length;i++){
            stat.execute("INSERT INTO 'types'('type') VALUES ('"+types[i]+"');");}
        }
        public static void delete_type(int id) throws SQLException {stat.execute("DELETE FROM 'types' WHERE id=='"+id+"';");}
    public  static void update_type(int id ,String type) throws SQLException {stat.execute("UPDATE 'types' SET 'type'=='"+type+"' WHERE id=='"+id+"';");}
        public  static void get_type(int id) throws SQLException {
            resSet=stat.executeQuery("SELECT * FROM 'types' WHERE id=="+id+";");
             while(resSet.next()){
                 String type = resSet.getString("type");
                 System.out.print(type);

             }

        }
    public static  void get_type(String where) throws SQLException {
            resSet=stat.executeQuery("SELECT * FROM 'types'  WHERE "+where+";");
        while(resSet.next())
        {
            String type = resSet.getString("type");
                System.out.println("type : "+ type );
            }
        }
    public static void get_all_types() throws SQLException {
            resSet =stat.executeQuery("SELECT * FROM types;");
            while(resSet.next()){
                String type = resSet.getString("type");
                System.out.println("type all: "+ type);
            }
    }
    public static void insert_cat(String name,String type,int age,Double weight) throws SQLException {
            resSet=stat.executeQuery("SELECT * FROM types;");
            int i=0;
            int id=1;
            while(resSet.next()){
                 i=0;
                if(type.equals(resSet.getString("type"))) {
                    id =resSet.getInt("id");
                    System.out.println(id);
                i++;
                stat.execute("INSERT INTO cats ('type_id','name','age','weight') VALUES ('"+id+"' ,'"+name+"' ,'"+age+"' , '"+weight+"' );");
                    break;
                }
                    }
                        if(i==0){
                            stat.execute("INSERT INTO 'types' ('type') VALUES ('"+type+"');");
                            resSet=stat.executeQuery("SELECT * FROM types;");
                            while(resSet.next()){
                                if(type.equals(resSet.getString("type"))){
                                    id =resSet.getInt("id");
                                    System.out.println(id);
                                   // stat.execute("INSERT INTO cats ('type_id','name','age','weight') VALUES ('"+id+"' ,'"+name+"' ,'"+age+"' , '"+weight+"' );");

                                    break;
                                }
                            }

                            stat.execute("INSERT INTO cats ('type_id','name','age','weight') VALUES ('"+id+"' ,'"+name+"' ,'"+age+"' , '"+weight+"' );");
                        }

                        if(i>0){i=0;}
    }
    static String[] names = {"Гарфилд","Том","Гудвин","Рокки","Ленивец","Пушок","Спорти","Бегемот","Пират","Гудини","Зорро","Саймон","Альбус","Базилио","Леопольд","Нарцисс","Атос","Каспер","Валлито","Оксфорд","Бисквит","Соня","Клеопатра","Цунами","Забияка","Матильда","Кнопка","Масяня","Царапка","Серсея","Ворсинка","Амели","Наоми","Маркиза","Изольда","Вальс","Несквик","Златан","Баскет","Изюм","Цукат","Мокко","Месси","Кокос","Адидас","Бейлиз","Тайгер","Зефир","Мохи","Валенсия","Баунти","Свити","Текила","Ириска","Карамель","Виски","Кукуруза","Гренка","Фасолька","Льдинка","Китана","Офелия","Дайкири","Брусника","Аватар","Космос","Призрак","Изумруд","Плинтус","Яндекс","Крисмас","Метеор","Оптимус","Смайлик","Цельсий","Краска","Дейзи","Пенка","Веста","Астра","Эйприл","Среда","Бусинка","Гайка","Елка","Золушка","Мята","Радость","Сиам","Оскар","Феликс","Гарри","Байрон","Чарли","Симба","Тао","Абу","Ватсон","Енисей","Измир","Кайзер","Васаби","Байкал","Багира","Айрис","Диана","Мими","Сакура","Индия","Тиффани","Скарлетт","Пикси","Лиззи","Алиса","Лило","Ямайка","Пэрис","Мальта","Аляска"};
    public  static  void add_more_cats(int n) throws SQLException {
            int maxName = names.length;
            for(int y=0;y<n;y++) {
               int randomN = rnd(maxName);
               int randomType = rnd(30);
               int randomAge = rnd(17);
               int randomWeight = rnd(57);
                stat.execute("INSERT INTO cats ('name','type_id','weight','age') VALUES ('"+names[randomN]+"','"+randomType+"', '"+randomWeight+"','"+randomAge+"');");
            }
    }
        public  static int  rnd(int max){
            return (int) (Math.random()*max);
        }

}
