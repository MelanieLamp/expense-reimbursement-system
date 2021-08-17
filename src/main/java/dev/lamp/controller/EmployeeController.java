package dev.lamp.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import dev.lamp.service.EmployeeService;
import dev.lamp.utilities.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.List;

public class EmployeeController {
    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private EmployeeService empService;
    private Gson gson;

    public EmployeeController() {
        this.empService = empService;
    }

    public Handler loginHandler = ctx -> {
        try{
            String username = ctx.pathParam("username");
            String password = ctx.pathParam("password");

            String body = ctx.body();
            Employee employee = gson.fromJson(body, Employee.class);

            employee = empService.login(username, password);
            String name = employee.getFname() + " " + employee.getLname();
            String jwt = JwtUtil.generate("employee", name);
            ctx.result(jwt);
            ctx.status(200);


        }catch(Exception e){
            e.printStackTrace();
            logger.error("Employee could not login");
            ctx.status(404);
        }
    };

    public Handler submitExpenseHandler = ctx -> {
        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);

            if(decodedJWT != null){
                String body = ctx.body();
                Gson gson = new Gson();
                Expense expense = gson.fromJson(body, Expense.class);
                empService.submitExpense(expense.getEmployeeId(), expense);
                String json = gson.toJson(expense);
                logger.info("Expense has submitted");
                ctx.result(json);
                ctx.status(201);
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Unable to submit expense at this time.");
        }
    };

    public Handler getEmployeeByIdHandler = ctx ->{
        String employeeId = ctx.pathParam("eid");
        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if(decodedJWT != null)
            {
                if(decodedJWT.getClaim("role").asString().equals("employee"))
                {
                    if(employeeId != null)
                    {
                        int eid = Integer.parseInt(employeeId);
                        Employee employee = empService.getEmployeeById(eid);
                        ctx.result(String.valueOf(employee));
                        ctx.status(200);
                    }
                }
                else{
                    logger.error("Employee id cannot be null");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            logger.error("Missing proper authorization permissions");
        }
    };

    public Handler getExpenseByEmployeeHandler = ctx ->{
        String employeeId = ctx.pathParam("eid");
        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if(decodedJWT != null)
            {
                if(decodedJWT.getClaim("role").asString().equals("employee"))
                {
                    if(employeeId != null)
                    {
                        int eid = Integer.parseInt(employeeId);
                        Employee employee = empService.getEmployeeById(eid);
                        List<Expense> expenseList = empService.getExpensesByEmployee(employee);
                        ctx.result(String.valueOf(expenseList));
                        ctx.status(200);
                    }
                }
                else{
                    logger.error("Employee id cannot be null");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            logger.error("Missing proper authorization permissions");
        }
    };
}
