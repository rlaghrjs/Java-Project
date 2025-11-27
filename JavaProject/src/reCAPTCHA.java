/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;

public class reCAPTCHA {
    
    public JFXPanel createCaptchaWindow() {
        // JavaFX 컴포넌트를 Swing에 통합하기 위해 JFXPanel 사용
        JFXPanel jfxPanel = new JFXPanel();
        
        // JavaFX 애플리케이션을 초기화 (JavaFX UI 구성)
        Platform.runLater(() -> {
            // WebView 생성 (reCAPTCHA를 보여줄 웹 페이지)
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load("https://www.google.com/recaptcha/api2/demo"); // reCAPTCHA 데모 페이지 로드
            
            // Scene을 생성하고 JFXPanel에 설정
            Scene scene = new Scene(webView, 400, 600); // Scene 크기 설정
            jfxPanel.setScene(scene);  // JFXPanel에 Scene 설정
        });

        return jfxPanel;  // JFXPanel 반환
    }

    // Test Swing application to show JFXPanel (for testing)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("reCAPTCHA Test");
            reCAPTCHA captcha = new reCAPTCHA();
            JFXPanel jfxPanel = captcha.createCaptchaWindow();
            
            frame.getContentPane().add(jfxPanel);  // JFXPanel을 JFrame에 추가
            frame.setSize(800, 600);  // JFrame 크기 설정
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

