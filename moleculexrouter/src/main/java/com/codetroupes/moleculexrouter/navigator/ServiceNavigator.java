package com.codetroupes.moleculexrouter.navigator;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * A service navigator
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public interface ServiceNavigator{

    <T extends IProvider> T service();

}
