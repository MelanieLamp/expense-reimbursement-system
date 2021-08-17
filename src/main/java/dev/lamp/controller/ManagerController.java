package dev.lamp.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.lamp.models.Expense;
import dev.lamp.models.Manager;
import dev.lamp.service.ManagerService;
import dev.lamp.utilities.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.List;

public class ManagerController {
    private static Logger logger = Logger.getLogger(ManagerController.class.getName());
    private static ManagerService manService;
    private Gson gson;

    public ManagerController() {
        this.manService = manService;
    }

    public Handler loginHandler = ctx -> {
        try{
            String body = ctx.body();
            Manager manager = gson.fromJson(body, Manager.class);
            manager = manService.login(manager.getManagerUsername(), manager.getManagerPassword());

            ctx.result(JwtUtil.generate(manager.getManagerUsername(),
                    manager.getManagerPassword()));
            ctx.status(200);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Manager could not login");
            ctx.status(404);
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

    public Handler getExpensesByStatusHandler = ctx ->{
        String employeeId = ctx.pathParam("eid");
        String status = ctx.queryParam("status", "NONE");

        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if(decodedJWT != null)
            {
                if(decodedJWT.getClaim("role").asString().equals("manager"))
                {
                    if(employeeId != null)
                    {
                        int eid = Integer.parseInt(employeeId);

                        List<Expense> expenseList = manService.getExpenseByStatus(eid, status);
                        ctx.result(String.valueOf(expenseList));
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

    public Handler updateExpense = ctx -> {
        String expenseId = ctx.pathParam("xid");
        String managerId = ctx.pathParam("mid");
        String status = ctx.pathParam("stat");
        String reason = ctx.pathParam("reason");

        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            if(decodedJWT != null){
                if(decodedJWT.getClaim("role").asString().equals("manager"))
                {
                    if(expenseId != null && managerId != null)
                    {
                        int xid = Integer.parseInt(expenseId);
                        int mid = Integer.parseInt(managerId);

                        String body = ctx.body();
                        Gson gson = new Gson();
                        Expense exp = gson.fromJson(body, Expense.class);

                        exp.setStatus(status);
                        exp.setReason(reason);
                        exp.setExpenseId(xid);

                        exp = manService.updateExpense(mid, exp.getExpenseId(), status, reason);
                        logger.info("Expense has been updated");
                        ctx.status(200);
                    }
                    else{
                        logger.error("Manager id cannot be null");
                    }
                }
                else{
                    logger.error("Missing proper authorization permissions");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            ctx.status(403);
            logger.error("Missing proper authorization permissions");
        }
    };
}
