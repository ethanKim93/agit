<template>

    <div class="layer" >  
      <div class="parent2" style="margin-top: 20%">
        <div class="child2" style="font-weight: 700;">
         ZOOM
        </div>
        <br>
      </div>
      <div class="parent2">
        <div class="child2">
          일상을 확대해 보세요
        </div>
        <br>
      </div>
  
    </div>
</template>

<script>
// import Buttons from '@/components/Buttons.vue'
export default {
  components: {
    // Buttons
  },data () {
    return {
      imageSrc: '~@/assets/back1.jpg',
    }
  }
}
</script>

<style>
#second-bg {
  position: fixed; 
  /* margin-top: 2000; */
  top: 2000; 
  left: 0; 
    
  /* Preserve aspet ratio */
  min-width: 100%;
  height: 700px;
  z-index: -1;
}
.parent2 {
    height: 4em; /* 애니메이션 높이 */
    overflow-y: hidden;
    display: block;
    
    
}

.parent2 .child2 {
    color: aliceblue;
    font-size: 50px;/* 애니메이션 높이 */
    font-weight: 400;
    line-height: 1;
    animation-delay: 2s;
    animation-name: grow; 
    animation-duration: 2.5s;/* 애니메이션 시간 */
   float : left;
   
    margin-left: 55%;
    
}

</style>