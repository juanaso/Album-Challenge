package challenge.juanaso.com.albumchallenge.di.module

import challenge.juanaso.com.albumchallenge.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    internal abstract fun bindMainFragment(): MainFragment
}
