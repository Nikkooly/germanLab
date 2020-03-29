package com.bstu.fit.lab16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String STUDENTS_XML_FILE = "students.xml";
    TextView mTextViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewInfo = (TextView) findViewById(R.id.text_view_info);

        try {
            // создаем и инициализируем XmlPullParser
            XmlPullParser parser = createXmlPullParser(STUDENTS_XML_FILE);

            // парсим xml файл в список объектов Student
            List<Student> students = parseXML(parser);

            mTextViewInfo.setText(students.toString());

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private XmlPullParser createXmlPullParser(String fileName) throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();

        // получаем доступ к xml файлу
        InputStream inputStream = getApplicationContext().getAssets().open(fileName);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);
        return parser;
    }

    private List<Student> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Student> students = new ArrayList<>();
        // получаем первое событие в xml файле
        int eventType = parser.getEventType();
        Student student = null;

        // итерационно обходим xml файл пока не наткнемся на тип события 'конец документа'
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = "";

            // обрабатываем события начала документа
            if (eventType == XmlPullParser.START_DOCUMENT) {
                students = new ArrayList<>();

                // парсим xml файл в список объектов Student
            } else if (eventType == XmlPullParser.START_TAG) {
                name = parser.getName();
                if (name.equals("Student")) {
                    student = new Student();
                    String id = parser.getAttributeValue(null, "id");
                    student.setId(Integer.parseInt(id));

                } else if (student != null) {
                    if (name.equals("name")) {
                        String value = parser.nextText();
                        student.setName(value);

                    } else if (name.equals("age")) {
                        String value = parser.nextText();
                        student.setAge(Integer.parseInt(value));

                    } else if (name.equals("language")) {
                        String value = parser.nextText();
                        student.setLanguage(value);
                    }
                }

                // доходим до конца XML файла
            } else if (eventType == XmlPullParser.END_TAG) {
                name = parser.getName();
                if (name.equalsIgnoreCase("Student") && student != null) {
                    students.add(student);
                }
            }

            // переходим к следующему событию внутри XML
            eventType = parser.next();
        }

        return students;

    }
}
