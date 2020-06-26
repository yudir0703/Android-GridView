package com.yudi.test2.app.modules

import com.yudi.test2.api.repository.ExploreRepositories
import com.yudi.test2.app.common.Constant
import com.yudi.test2.app.ui.viewmodel.ExploreViewModel
import com.yudi.test2.service.api.APIInterface
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author Yudi Rahmat
 */

val AppModule: Module = module {
    single {
        APIInterface(
            Constant.BASE_URL,
            androidContext()
        )
    }

    single {
        ExploreRepositories(
            get(),
            CompositeDisposable()
        )
    }

    viewModel {
        ExploreViewModel(get())
    }
}