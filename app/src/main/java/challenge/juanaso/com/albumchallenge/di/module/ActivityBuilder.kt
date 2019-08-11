package challenge.juanaso.com.albumchallenge.di.module

import challenge.juanaso.com.albumchallenge.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    internal abstract fun contributeMainActivity(): MainActivity
}