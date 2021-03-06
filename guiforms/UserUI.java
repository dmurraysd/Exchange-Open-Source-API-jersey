/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.guiforms;

import aping.apicalls.ApiCalls;
import aping.authentication.Authentication;
import aping.exceptions.APINGException;
import aping.exchangeapp.AppMain;
import aping.apiclient.APIClient;
import aping.enums.ExecutionReportStatus;
import aping.enums.MarketProjection;
import aping.enums.MarketSort;
import aping.enums.MatchProjection;
import aping.enums.OrderProjection;
import aping.enums.OrderType;
import aping.enums.PersistenceType;
import aping.enums.PriceData;
import aping.enums.Side;
import aping.jsonentities.EventTypeResult;
import aping.jsonentities.LimitOrder;
import aping.jsonentities.MarketBook;
import aping.jsonentities.MarketCatalogue;
import aping.jsonentities.MarketFilter;
import aping.jsonentities.PlaceExecutionReport;
import aping.jsonentities.PlaceInstruction;
import aping.jsonentities.PlaceInstructionReport;
import aping.jsonentities.PriceProjection;
import aping.jsonentities.Runner;
import aping.jsonentities.RunnerCatalog;
import aping.jsonentities.TimeRange;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David_killa
 */
public class UserUI extends javax.swing.JFrame {

    /**
     * Creates new form UserUI
     */
    
    private APIClient localClient;
    private Authentication localAuth;
    private ApiCalls localApiOperations;
    private List<MarketCatalogue> marketCatalogueResult;
    private Set<String> eventTypeIds;
    private MarketFilter marketFilter;
    private List<MarketBook> marketBookReturn;
    private String marketIdChosen;
    
    public UserUI() {
        initComponents();
        localClient = new APIClient();
        localAuth = new Authentication(localClient);
        localApiOperations = new ApiCalls(localClient);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionPanel = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        keepAliveBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        listPanel = new javax.swing.JPanel();
        viewEventTypesBtn = new javax.swing.JButton();
        marketBookBtn = new javax.swing.JButton();
        placeOrderBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        optionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("   API Options"));

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        optionPanel.add(loginBtn);

        keepAliveBtn.setText("KeepAlive");
        keepAliveBtn.setEnabled(false);
        keepAliveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keepAliveBtnActionPerformed(evt);
            }
        });
        optionPanel.add(keepAliveBtn);

        logOutBtn.setText("LogOut");
        logOutBtn.setEnabled(false);
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });
        optionPanel.add(logOutBtn);

        listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Options "));

        viewEventTypesBtn.setText("View Event Types");
        viewEventTypesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEventTypesBtnActionPerformed(evt);
            }
        });
        listPanel.add(viewEventTypesBtn);

        marketBookBtn.setText("View Market Book");
        marketBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marketBookBtnActionPerformed(evt);
            }
        });
        listPanel.add(marketBookBtn);

        placeOrderBtn.setText("Place Order");
        placeOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderBtnActionPerformed(evt);
            }
        });
        listPanel.add(placeOrderBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(optionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        // TODO add your handling code here:
         try {
            localAuth.logOut();
        } catch (Exception ex) 
        {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loginBtn.setEnabled(true);
        this.keepAliveBtn.setEnabled(false);
        this.logOutBtn.setEnabled(false);
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void keepAliveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keepAliveBtnActionPerformed
        // TODO add your handling code here:
        try {
            localAuth.stayAlive();
        } catch (Exception ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_keepAliveBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        try {
            /**/
            localAuth.Login("Username Here", "Password here");
        } catch (Exception ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loginBtn.setEnabled(false);
        this.keepAliveBtn.setEnabled(true);
        this.logOutBtn.setEnabled(true);
    }//GEN-LAST:event_loginBtnActionPerformed

    private void viewEventTypesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEventTypesBtnActionPerformed
        // TODO add your handling code here:
        try
        {
        
        marketFilter = new MarketFilter();
        eventTypeIds = new HashSet<String>();
        //marketFilter.setInPlayOnly(true);
        System.out.println("1.(listEventTypes) Get all Event Types...\n");
        List<EventTypeResult> r = localApiOperations.listEventTypes(marketFilter);
        System.out.println("2. Extract Event Type Id for Horse Racing...\n");
        String h = String.valueOf(r.get(1).getMarketCount());
        System.out.println("2. Extract Event Type Id for Horse Racing..." + h);
        for (EventTypeResult eventTypeResult : r) {
            if(eventTypeResult.getEventType().getName().equals("Soccer")){
                System.out.println("3. EventTypeId for \"Horse Racing\" is: " + eventTypeResult.getEventType().getId()+"\n");
                eventTypeIds.add(eventTypeResult.getEventType().getId().toString());
            }
        }
        }catch(APINGException apiException)
        {
            System.out.println(apiException.toString());
        }
    }//GEN-LAST:event_viewEventTypesBtnActionPerformed

    private void marketBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marketBookBtnActionPerformed
        // TODO add your handling code here:
         /**
             * ListMarketCatalogue: Get next available horse races, parameters:
             * eventTypeIds : 7 - get all available horse races for event id 7 (horse racing)
             * maxResults: 1 - specify number of results returned (narrowed to 1 to get first race)
             * marketStartTime: specify date (must be in this format: yyyy-mm-ddTHH:MM:SSZ)
             * sort: FIRST_TO_START - specify sort order to first to start race
             */
            System.out.println("4.(listMarketCataloque) Get next horse racing market in the UK...\n");
            TimeRange time = new TimeRange();
            time.setFrom(new Date());

            Set<String> countries = new HashSet<String>();
            countries.add("GB");

            Set<String> typesCode = new HashSet<String>();
            typesCode.add("MATCH_ODDS");

            //marketFilter = new MarketFilter();
            marketFilter.setEventTypeIds(eventTypeIds);
            marketFilter.setMarketStartTime(time);
            marketFilter.setMarketCountries(countries);
            marketFilter.setMarketTypeCodes(typesCode);

            Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
            marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

            String maxResults = "1";
            marketCatalogueResult = null;
            try
            {
                marketCatalogueResult = localApiOperations.listMarketCatalogue(marketFilter, marketProjection, 
                                                                                                        MarketSort.LAST_TO_START, maxResults);
            }catch(APINGException apiException)
            {
                System.out.println(apiException.toString());
            }
            

            System.out.println("5. Print static marketId, name and runners....\n");
            printMarketCatalogue(marketCatalogueResult.get(0));
            /**
             * ListMarketBook: get list of runners in the market, parameters:
             * marketId:  the market we want to list runners
             *
             */
            System.out.println("6.(listMarketBook) Get volatile info for Market including best 3 exchange prices available...\n");
            marketIdChosen = marketCatalogueResult.get(0).getMarketId();

            PriceProjection priceProjection = new PriceProjection();
            Set<PriceData> priceData = new HashSet<PriceData>();
            priceData.add(PriceData.EX_BEST_OFFERS);
            priceProjection.setPriceData(priceData);

            //In this case we don't need these objects so they are declared null
            OrderProjection orderProjection = null;
            MatchProjection matchProjection = null;
            String currencyCode = null;

            List<String> marketIds = new ArrayList<String>();
            marketIds.add(marketIdChosen);
            try
            {
                marketBookReturn = localApiOperations.listMarketBook(marketIds, priceProjection, orderProjection, 
                                                                                    matchProjection, currencyCode);
            }catch(APINGException apiException)
            {
                System.out.println(apiException.toString());
            }
            
    }//GEN-LAST:event_marketBookBtnActionPerformed

    private void placeOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderBtnActionPerformed
        // TODO add your handling code here:
         /**
             * PlaceOrders: we try to place a bet, based on the previous request we provide the following:
             * marketId: the market id
             * selectionId: the runner selection id we want to place the bet on
             * side: BACK - specify side, can be Back or Lay
             * orderType: LIMIT - specify order type
             * size: the size of the bet
             * price: the price of the bet
             * customerRef: 1 - unique reference for a transaction specified by user, must be different for each request
             *
             */

            long selectionId = 0;
            if ( marketBookReturn.size() != 0 ) {
                Runner runner = marketBookReturn.get(0).getRunners().get(1);
                List<RunnerCatalog> runnerCatalogList = marketCatalogueResult.get(0).getRunners();
                selectionId = runner.getSelectionId();
                System.out.println("7. Place a bet below minimum stake to prevent the bet actually " +
                        "being placed for marketId: "+marketIdChosen+" with selectionId: "+selectionId+ " and name: " + runnerCatalogList.get(1).getRunnerName() +"...\n\n");
                List<PlaceInstruction> instructions = new ArrayList<PlaceInstruction>();
                PlaceInstruction instruction = new PlaceInstruction();
                instruction.setHandicap(0);
                instruction.setSide(Side.BACK);
                instruction.setOrderType(OrderType.LIMIT);

                LimitOrder limitOrder = new LimitOrder();
                limitOrder.setPersistenceType(PersistenceType.LAPSE);
                //API-NG will return an error with the default size=0.01. This is an expected behaviour.
                //You can adjust the size and price value in the "apingdemo.properties" file
                limitOrder.setPrice(900);
                limitOrder.setSize(2.00);

                instruction.setLimitOrder(limitOrder);
                instruction.setSelectionId(selectionId);
                instructions.add(instruction);

                String customerRef = "1";
                try{
                PlaceExecutionReport placeBetResult = localApiOperations.placeOrders(marketIdChosen, instructions, customerRef);

                // Handling the operation result
                if (placeBetResult.getStatus() == ExecutionReportStatus.SUCCESS) {
                    System.out.println("Your bet has been placed!!");
                    //PlaceInstructionReport pir = placeBetResult.getInstructionReports();
                    System.out.println(placeBetResult.getInstructionReports().get(0).getStatus());
                } else if (placeBetResult.getStatus() == ExecutionReportStatus.FAILURE) {
                    System.out.println("Your bet has NOT been placed :*( ");
                    System.out.println("The error is: " + placeBetResult.getErrorCode() + ": " + placeBetResult.getErrorCode().getMessage());
                }
           

                } catch (APINGException apiExc) {
                    System.out.println(apiExc.toString());
                }
         } 
         else 
         {
                System.out.println("Sorry, no runners found\n\n");
         }
    }//GEN-LAST:event_placeOrderBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton keepAliveBtn;
    private javax.swing.JPanel listPanel;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton marketBookBtn;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JButton placeOrderBtn;
    private javax.swing.JButton viewEventTypesBtn;
    // End of variables declaration//GEN-END:variables

    private void printMarketCatalogue(MarketCatalogue mk) 
    {
        System.out.println("Market Name: "+mk.getMarketName() + "; Id: "+mk.getMarketId()+"\n");
        List<RunnerCatalog> runners = mk.getRunners();
        if(runners!=null){
            for(RunnerCatalog rCat : runners){
                System.out.println("Runner Name: "+rCat.getRunnerName()+"; Selection Id: "+rCat.getSelectionId()+"\n");
            }
        }    }
}
