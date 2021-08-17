package dev.lamp.main;

import dev.lamp.controller.EmployeeController;
import dev.lamp.controller.ManagerController;
import io.javalin.Javalin;

public class App
{
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                }
        );
        ManagerController managerController = new ManagerController();
        EmployeeController employeeController = new EmployeeController();

        app.post("/employee/login/?username=?&password=?", employeeController.loginHandler);
        app.post("/employee/submit", employeeController.submitExpenseHandler);
        app.get("/employee/expenses/:eid", employeeController.getExpenseByEmployeeHandler);

        app.post("/manager/login/?username=?&password=?", managerController.loginHandler);
        app.get("/manager/expenses/:xid/:eid", managerController.getExpensesByIdHandler);
        app.get("/manager/expenses/all", managerController.getAllExpensesHandler);
        app.post("/manager/expenses/update/:xid/:eid", managerController.updateExpense);

        app.start();
    }
}
