package Login;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.Icon;

public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Dashboard frame = new Dashboard();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setBounds(188, 0, 598, 563);
        mainContent.setLayout(null);
        contentPane.add(mainContent);

        JLabel lblWelcome = new JLabel("Good Day Administrator!");
        lblWelcome.setBackground(new Color(0, 64, 0));
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(-11, 11, 294, 50);
        mainContent.add(lblWelcome);

        JLabel lblWelcome_1 = new JLabel("Dashboard");
        lblWelcome_1.setForeground(new Color(0, 0, 160));
        lblWelcome_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome_1.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblWelcome_1.setBounds(10, 47, 130, 50);
        mainContent.add(lblWelcome_1);

       
        JPanel chartPanel = createEnhancedLineChartPanel();
        chartPanel.setBounds(25, 260, 544, 292); 
        mainContent.add(chartPanel);

       
        JPanel donutChartPanel = createDonutChartPanel();
        donutChartPanel.setBounds(56, 94, 513, 154); 
        mainContent.add(donutChartPanel);
        
        JLabel lblNewLabel_1 = new JLabel((Icon) null);
        lblNewLabel_1.setBounds(376, 0, 212, 187);
        mainContent.add(lblNewLabel_1);

        JPanel sidebar = new JPanel();
        sidebar.setBounds(10, 0, 180, 563);
        contentPane.add(sidebar);
        sidebar.setBackground(new Color(0, 0, 0)); 
        sidebar.setLayout(null);

        JLabel lblWelcome_1_1 = new JLabel("DASHBOARD");
        lblWelcome_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblWelcome_1_1.setForeground(Color.WHITE);  
        lblWelcome_1_1.setBounds(25, 82, 130, 50);
        sidebar.add(lblWelcome_1_1);

       
        JLabel lblMenuItem = new JLabel("MENU ITEM");
        lblMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuItem.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblMenuItem.setForeground(Color.WHITE);  
        lblMenuItem.setBounds(25, 180, 130, 50);
        sidebar.add(lblMenuItem);

        lblMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuItemForm menuItemForm = new MenuItemForm();
                menuItemForm.setVisible(true);
            }
        });
        JLabel lblOrders = new JLabel("ORDERS");
        lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrders.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOrders.setForeground(Color.WHITE);  
        lblOrders.setBounds(25, 281, 130, 50);
        sidebar.add(lblOrders);

        lblOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OrdersForm ordersForm = new OrdersForm();
                ordersForm.setVisible(true); 
            }
        });



        JLabel lblLogout = new JLabel("LOGOUT");
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblLogout.setForeground(Color.WHITE); 
        lblLogout.setBounds(25, 466, 130, 50);
        sidebar.add(lblLogout);
        
        JLabel lblEmployeesMenu = new JLabel("EMPLOYEES");
        lblEmployeesMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmployeesMenu.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEmployeesMenu.setForeground(Color.WHITE);  
        lblEmployeesMenu.setBounds(25, 378, 130, 50);
        lblEmployeesMenu.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        lblEmployeesMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openEmployeesForm();  
            }
            private void openEmployeesForm() {
                EmployeesForm employeesForm = new EmployeesForm();
                employeesForm.setVisible(true);  
            }
        });
        sidebar.add(lblEmployeesMenu);

      
        addHoverEffect(lblMenuItem);
        addHoverEffect(lblOrders);
        addHoverEffect(lblLogout);
        addHoverEffect(lblEmployeesMenu);

        contentPane.revalidate();
        contentPane.repaint();
    }

    protected void openOrdersFrame() {
		// TODO Auto-generated method stub
		
	}

	private void addHoverEffect(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.YELLOW); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.WHITE); 
            }
        });
    }


    private JPanel createEnhancedLineChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(40, "Sales", "JUNUARY");
        dataset.addValue(30, "Sales", "FEBRUARY");
        dataset.addValue(20, "Sales", "MARCH");
        dataset.addValue(10, "Sales", "APRIL");
        dataset.addValue(0, "Sales", "MAY");

        
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Monthly",        
                "Year",                    
                "",                    
                dataset,                    
                PlotOrientation.VERTICAL,  
                true,                    
                true,                      
                false                  
        );

       
        lineChart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();

     
        plot.setBackgroundPaint(Color.WHITE);

      
        plot.setDomainGridlinePaint(Color.GRAY);  
        plot.setRangeGridlinePaint(Color.GRAY);   
       
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);  
        renderer.setSeriesShapesVisible(0, true); 
        renderer.setSeriesPaint(0, Color.BLUE);  
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10)); 
        renderer.setSeriesStroke(0, new BasicStroke(3.0f)); 

        plot.setRenderer(renderer);

        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 16));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 16));


        plot.getDomainAxis().setAxisLinePaint(Color.BLUE);
        plot.getRangeAxis().setAxisLinePaint(Color.BLUE); 
        plot.getDomainAxis().setTickMarkPaint(Color.BLUE); 
        plot.getRangeAxis().setTickMarkPaint(Color.BLUE);  

   
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        chartPanel.setMouseWheelEnabled(true);

        return chartPanel;
    }

   
    @SuppressWarnings("deprecation")
	private JPanel createDonutChartPanel() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Users", 3); 
        dataset.setValue("Employees", 6);

       
        JFreeChart donutChart = ChartFactory.createRingChart(
                "Total Count", 
                dataset, 
                true, 
                true, 
                false 
        );

        // Customize the chart
        RingPlot plot = (RingPlot) donutChart.getPlot();
        plot.setSectionDepth(0.30);
        plot.setSectionPaint(0, new Color(50, 150, 255)); 
        plot.setSectionPaint(1, new Color(255, 80, 80)); 

       
        ChartPanel chartPanel = new ChartPanel(donutChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(250, 150));
        chartPanel.setMouseWheelEnabled(true);
        
        JLabel lblNewLabel_1 = new JLabel((Icon) null);
        chartPanel.add(lblNewLabel_1);

        return chartPanel;
    }
}