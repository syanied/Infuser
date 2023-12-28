package Infuser.Development_Kit.Infuser_Swing_Control;

import javax.swing.*;

import Infuser.UI_UX.InfuserUI;

/**
 *
 * Copyright 2023, Syanide, All rights reserved.
 * <p>
 * API implementation for [Infuser] application version x1.0
 * @author Syanide.
 *
 */

public class MainInterface {

    /**
     * Gets the Prompt area from the Main interface
     * @return (UI[<.>] Main interface) Prompt area.
     */
    public JTextArea getPromptArea() {
        return InfuserUI.getPromptArea();
    }

    /**
     * Gets the TextField from the Main interface
     * @return (UI[<.>] Main interface) TextField.
     */

    public JTextField getTextField() {
        return InfuserUI.getTextField();
    }
}
