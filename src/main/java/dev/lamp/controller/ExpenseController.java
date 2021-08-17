package dev.lamp.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.lamp.models.Expense;
import dev.lamp.service.EmployeeService;
import dev.lamp.service.ExpenseService;
import dev.lamp.service.ManagerService;
import dev.lamp.utilities.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.List;

public class ExpenseController
{
    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private ExpenseService expService;
    private EmployeeService empService;
    private ManagerService manService;

    public Handler getAllExpensesHandler = ctx -> {
        String manager_id = ctx.queryParam("mid", "NONE");
        String status = ctx.queryParam("status", "NONE");
        String expensesJSON;
        try {
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);

            if(decodedJWT != null)
            {
                if(decodedJWT.getClaim("role").asString().equals("manager"))
                {
                    if(manager_id.equals("NONE") || status.equals("NONE"))
                    {
                        List<Expense> allExpenses = this.manService.getAllExpenses();
                        Gson gson = new Gson();
                        String expenseJSON = gson.toJson(allExpenses);
                        ctx.result(expenseJSON);
                        ctx.status(200);
                    }
                    else{
                        if(manager_id != null){
                            int m_id = Integer.parseInt(manager_id);
                            List<Expense> expenses = this.manService.getExpenseByStatus(m_id, status);
                            Gson gson = new Gson();
                            String statusJSON = gson.toJson(expenses);
                            ctx.result(statusJSON);
                            ctx.status(200);
                        }
                        else{
                            logger.error("Manager id cannot be null");
                        }

                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            logger.error("Missing proper authorization permissions");
        }
    };

    public Handler getExpensesByIdHandler = ctx ->{
        String expenseId = ctx.pathParam("xid");
        String employeeId = ctx.pathParam("eid");

        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if(decodedJWT != null)
            {
                if(decodedJWT.getClaim("role").asString().equals("manager"))
                {
                    if(expenseId != null && employeeId != null)
                    {
                        int xid = Integer.parseInt(expenseId);
                        int eid = Integer.parseInt(employeeId);

                        Expense expense = manService.getExpenseById(eid, xid);
                        ctx.result(String.valueOf(expense));
                        ctx.status(200);
                    }
                }
                else{
                    logger.error("Manager id cannot be null");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            logger.error("Missing proper authorization permissions");
        }
    };
}
