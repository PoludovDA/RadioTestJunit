package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class MainTrainingPage {
    private final static ElementsCollection tasks = $$x("//h3/a");

    public MainTrainingPage() {
        open("http://uitestingplayground.com/");
    }

    @Step("Выбор задачи {task}")
    public TaskPage chooseTask(String task) {
        tasks.stream().filter(x -> x.text().equals(task)).findFirst().get().click();
        return new TaskPage();
    }
}
