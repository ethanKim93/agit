<template>
    <div class="chat-form">
        <input
            v-if="this.$store.state.userName"
            class="chat-input"
            v-model="msg"
            label="chat"
            placeholder="메세지를 입력하세요."
            @keyup.enter="sendMessage"
        />
        <va-icon name="send" @click="sendMessage" />
    </div>
</template>

<script>
export default {
    name: "ChatForm",
    data() {
        return {
            msg: "",
        };
    },
    // created() {
    //     axios
    //         .get(`/api/user/${this.$store.state.userId}`)
    //         .then(({ data: userObj }) => {
    //             this.myName = `${userObj.name}`;
    //         });
    // },
    props: {},
    methods: {
        sendMessage() {
            if (this.msg.trim()) {
                let message = `${this.$store.state.userName},${this.msg}`;
                this.$parent.sendMessage(message);
            }
            this.msg = "";

            // await axios
            //     .get(`/api/user/${this.$store.state.userId}`)
            //     .then(({ data: userObj }) => {

            //     });
        },
    },
};
</script>
<style scoped>
.chat-form {
    display: flex;
    justify-content: space-between;
    padding: 1.4rem;
    background: #ffffff;
    border-radius: 30px 30px 24px 24px;
    box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}

.chat-input {
    border: none;
    padding: 0.5rem;
    font-size: 16px;
}

.chat__input:focus {
    outline: none;
}
</style>
