package com.fastacash.opensdk.template;

import android.view.View;
import android.view.ViewGroup;

import com.fastacash.opensdk.controller.APIConfig;

/**
 * Created by nikhil on 10/26/2015.
 */
public class TemplateManager {

    public void loadTemplate(Template template) {
        if (template != null) {
            //getes the both parent view & template view from APiConfig & template respectively.
            View buttonElement = APIConfig.getInstance().getButtonElement();
            if (buttonElement != null) {
                //retrieves the template view from the Template & add it to buttonelement.
                ((ViewGroup) buttonElement).addView(template.getTemplateView());
            }
        }

    }
}
