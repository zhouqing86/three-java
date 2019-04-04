package rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SimpleObservable {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("Observable emit 1" + "\n");
                e.onNext(1);
                System.out.println("Observable emit 2" + "\n");
                e.onNext(2);
                System.out.println("Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                System.out.println("Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext, i=" + i);
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete" + "\n" );
            }
        });
    }
}
