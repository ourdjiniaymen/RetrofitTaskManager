����   2 | Vcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment$onActivityCreated$2  ;androidx/recyclerview/widget/ItemTouchHelper$SimpleCallback  Bcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment  onActivityCreated (Landroid/os/Bundle;)V   onMove �(Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z #Lorg/jetbrains/annotations/NotNull; recyclerView  kotlin/jvm/internal/Intrinsics  checkParameterIsNotNull '(Ljava/lang/Object;Ljava/lang/String;)V  
   
viewHolder  target  this XLcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment$onActivityCreated$2; +Landroidx/recyclerview/widget/RecyclerView; 6Landroidx/recyclerview/widget/RecyclerView$ViewHolder; onSwiped :(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V $adapter 3Lcom/example/tdm2_td02_exo2/adapter/ContactAdapter;   	  ! 4androidx/recyclerview/widget/RecyclerView$ViewHolder # getAdapterPosition ()I % &
 $ ' 1com/example/tdm2_td02_exo2/adapter/ContactAdapter ) getContactAt 3(I)Lcom/example/tdm2_td02_exo2/data/entity/Contact; + ,
 * - this$0 DLcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment; / 0	  1 access$getViewModel$p �(Lcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment;)Lcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactViewModel; 3 4
  5 Ccom/example/tdm2_td02_exo2/ui/main/listContact/ListContactViewModel 7 deleteContact 3(Lcom/example/tdm2_td02_exo2/data/entity/Contact;)V 9 :
 8 ; getActivity *()Landroidx/fragment/app/FragmentActivity; = >
  ? android/content/Context A Contact supprimé C java/lang/CharSequence E android/widget/Toast G makeText J(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast; I J
 H K show ()V M N
 H O it 0Lcom/example/tdm2_td02_exo2/data/entity/Contact; <$i$a$-let-ListContactFragment$onActivityCreated$2$onSwiped$1 I 	direction .com/example/tdm2_td02_exo2/data/entity/Contact V <init> |(Lcom/example/tdm2_td02_exo2/ui/main/listContact/ListContactFragment;Lcom/example/tdm2_td02_exo2/adapter/ContactAdap