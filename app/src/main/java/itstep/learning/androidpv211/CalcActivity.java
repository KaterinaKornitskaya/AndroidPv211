package itstep.learning.androidpv211;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalcActivity extends AppCompatActivity {

    private TextView tvExpression;
    private TextView tvResult;
    private String zero;
    private String operator = "";
    private boolean isNewOperation = true;
    private String firstNumber = "";
    private String secondNumber = "";

    private String history = "";

    private Boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvExpression = findViewById(R.id.calc_tv_expression);
        tvResult = findViewById(R.id.calc_tv_result);
        zero = getString(R.string.calc_btn_0);

        // на кнопку С вішаємо обробник onClearClick (наша ф-ія описана нижче)
        Button btnC = findViewById(R.id.calc_btn_c);
        btnC.setOnClickListener(this::onClearClick);

        findViewById(R.id.calc_btn_0).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_1).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_2).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_3).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_4).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_5).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_6).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_7).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_8).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_9).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_comma).setOnClickListener(this::onDigitClick);
        findViewById(R.id.calc_btn_plus_minus).setOnClickListener(this::onDigitClick);



        // Назначаем обработчики для кнопок операций (+, -, =)
        findViewById(R.id.calc_btn_plus).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.calc_btn_minus).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.calc_btn_multiplication).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.calc_btn_division).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.calc_btn_equals).setOnClickListener(this::onEqualsClick);

        // стартовий скид при запуску
        if(savedInstanceState == null){  // якщо це перший запуск
            onClearClick(btnC);
        }

    }

    private void onDigitClick(View view){

        if(isNew){
            tvResult.setText("");
        }
        isNew = false;
        // берем текст який вже є в полі резалт
        String result = tvResult.getText().toString();
        //history = result;

        // я якщо резалт був 0 - то не враховуємо його, берем
        // попередній резалт просто як пусто строку
        if(result.equals(zero)){
            result = "";
            history = result;
        }

        // беремо текст з клікнутої кнопки, додаємо до тексту в резалт
        // якщо нажати +/- і tvResult НЕ починаєтья з -
        if( (!tvResult.getText().toString().startsWith("-"))
                && (((Button)view).getText()).equals("\u00B1")){
            result = "-" + result;
            history = result;
        }
        // якщо нажати +/- і tvResult починаєтья з -
        else if(tvResult.getText().toString().startsWith("-")
                && (((Button)view).getText()).equals("\u00B1")){
            result += "";
            history += "";
        }
        // якщо в tvResult вже є точка і точка нажата ще раз
        else if( isDotPresent(tvResult.getText().toString())
                && (((Button)view).getText()).equals(".")
        ){
            result += "";
            history += "";
        }
        else{
            result += ((Button)view).getText();
            history += ((Button)view).getText();
        }

        //history += result;
        tvExpression.setText(history);

        // устанавлюєм новий резалт
        tvResult.setText(result);

    }

    private Boolean isDotPresent(String str){
        if(str.indexOf(".") == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Обработчик нажатия на операторы (+, -)
     */
    private void onOperatorClick(View view) {
        isNew = true;
        operator = ((Button) view).getText().toString();
        firstNumber = tvResult.getText().toString();
        history += operator;
        tvExpression.setText(history);

    }


    private void onEqualsClick(View view) {
        String newNumber = tvResult.getText().toString();
        double res = 0.0;

        switch (operator){
            case "\u002B":
                res = Double.parseDouble(firstNumber) + Double.parseDouble(newNumber);
                firstNumber += newNumber;
                break;
            case "\u2212":
                res = Double.parseDouble(firstNumber) - Double.parseDouble(newNumber);
                break;
            case "\u00D7":
                res = Double.parseDouble(firstNumber) * Double.parseDouble(newNumber);
                break;
            case "\u00F7":
                if( (Double.parseDouble(newNumber) == 0 )){
                    res = 0.0;
                }
                else{
                    res = Double.parseDouble(firstNumber) / Double.parseDouble(newNumber);
                }
                break;
        }
        // при нажатии = в history добавляем =
        history += "=";
        tvExpression.setText(history);

        tvResult.setText(String.valueOf(res));
        // после вычисления результата в history
        // переприсваиваем результат
        history = String.valueOf(res);
    }



    private void onClearClick(View view){
        history = "";
        tvExpression.setText(history);  // Очищаем выражение
        tvResult.setText("0");     // Сбрасываем результат на 0
        //firstNumber = 0;           // Сбрасываем запомненное число
        operator = "";             // Сбрасываем оператор
        isNewOperation = true;     // Указываем, что следующая операция новая
    }

    // спрацьовують коли міняється ресурс
    // у нас один java код на різні xml (у нас зараз є два файли xml -
    // на вертикальну і горизонтальну розмітку
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // цей метод викликається, коли замінюється конфігурація (напр. поворот пристрою, зміна мови)
        // надає Bundle outState - як "сховище" дял збереження даних
        super.onSaveInstanceState(outState);
        outState.putCharSequence("result", tvResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // цей метод викликається, коли впроваджена нові конфігурація
        // передає savedInstanceState == outState, який зберігався при виході
        super.onRestoreInstanceState(savedInstanceState);
        tvResult.setText(savedInstanceState.getCharSequence("result"));
    }
}